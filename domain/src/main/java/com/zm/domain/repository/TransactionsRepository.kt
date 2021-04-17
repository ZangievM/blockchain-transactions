package com.zm.domain.repository

import com.zm.domain.util.TransactionResource
import kotlinx.coroutines.flow.Flow

interface TransactionsRepository {
    fun subscribeToTransactions(): Flow<TransactionResource<out String>>
    fun unsubscribeFromTransactions()
    fun connect()
    fun disconnect()
}