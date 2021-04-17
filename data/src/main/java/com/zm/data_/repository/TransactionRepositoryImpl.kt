package com.zm.data_.repository

import com.google.gson.Gson
import com.zm.data_.mappers.toTransaction
import com.zm.data_.model.api.TransactionDataModel
import com.zm.domain.model.Transaction
import com.zm.domain.repository.TransactionsRepository
import com.zm.domain.util.TransactionCommands
import com.zm.domain.util.TransactionResource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.*
import javax.inject.Inject

@ExperimentalCoroutinesApi
class TransactionRepositoryImpl @Inject constructor(
    private val client: OkHttpClient,
    private val transactionsUrl: String,
    private val gson: Gson
) : TransactionsRepository {

    private var eventsListener: TransactionsRepository.Listener? = null
    private val listener = object : WebSocketListener() {
        override fun onOpen(webSocket: WebSocket, response: Response) {
            super.onOpen(webSocket, response)
            socket?.send(TransactionCommands.SUBSCRIBE.value)
            eventsListener?.onNewEvent(TransactionResource.Connected())
        }

        override fun onMessage(webSocket: WebSocket, text: String) {
            super.onMessage(webSocket, text)
            val t = parse(text)
            eventsListener?.onNewEvent(TransactionResource.NewData(t))
        }

        override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
            super.onClosed(webSocket, code, reason)
            eventsListener?.onNewEvent(TransactionResource.Disconnected())
            disconnect()
        }

        override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
            super.onFailure(webSocket, t, response)
            eventsListener?.onNewEvent(TransactionResource.Failure(t))
            disconnect()
        }
    }

    private fun parse(text: String): Transaction {
        val tData = gson.fromJson(text, TransactionDataModel::class.java)
        return tData.toTransaction()
    }

    @Volatile
    private var socket: WebSocket? = null

    @Synchronized
    override fun subscribeToTransactions(listener: TransactionsRepository.Listener) {
        if (socket == null) connect()
        this.eventsListener = listener
    }

    @Synchronized
    override fun unsubscribeFromTransactions(listener: TransactionsRepository.Listener) {
        eventsListener?.onNewEvent(TransactionResource.Disconnected())
        eventsListener = null
        disconnect()
    }

    @Synchronized
    private fun connect() {
        val request = Request.Builder()
            .url(transactionsUrl)
            .build()
        socket = client.newWebSocket(request, listener)
    }

    @Synchronized
    private fun disconnect() {
        socket?.cancel()
        socket = null
    }
}