package com.zm.data_.repository

import com.zm.domain.repository.TransactionsRepository
import com.zm.domain.util.TransactionCommands
import com.zm.domain.util.TransactionResource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import okhttp3.*
import javax.inject.Inject

@ExperimentalCoroutinesApi
class TransactionRepositoryImpl @Inject constructor(
    private val client: OkHttpClient,
    private val transactionsUrl: String
) : TransactionsRepository {
    private val listener = object : WebSocketListener() {
        override fun onOpen(webSocket: WebSocket, response: Response) {
            super.onOpen(webSocket, response)
            eventsFlow.value = TransactionResource.Connected()
            socket?.send(TransactionCommands.SUBSCRIBE.value)
        }

        override fun onMessage(webSocket: WebSocket, text: String) {
            super.onMessage(webSocket, text)
            eventsFlow.value = TransactionResource.NewData(text)
        }

        override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
            super.onClosed(webSocket, code, reason)
            eventsFlow.value = TransactionResource.Disconnected()
        }

        override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
            super.onFailure(webSocket, t, response)
            eventsFlow.value = TransactionResource.Failure(t)
        }
    }

    @Volatile
    private var socket: WebSocket? = null

    private var eventsFlow =
        MutableStateFlow<TransactionResource<out String>>(TransactionResource.Disconnected())

    @Synchronized
    override fun subscribeToTransactions(): Flow<TransactionResource<out String>> {
        if (socket == null) connect()
        return eventsFlow
    }

    @Synchronized
    override fun unsubscribeFromTransactions() {
        disconnect()
    }

    @Synchronized
    override fun connect() {
        val request = Request.Builder()
            .url(transactionsUrl)
            .build()
        socket = client.newWebSocket(request, listener)
    }

    @Synchronized
    override fun disconnect() {
        socket?.cancel()
        socket = null
    }
}