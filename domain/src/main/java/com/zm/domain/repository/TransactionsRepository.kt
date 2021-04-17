package com.zm.domain.repository

import com.zm.domain.model.Transaction
import com.zm.domain.util.TransactionResource

interface TransactionsRepository {
    fun subscribeToTransactions(listener: Listener)
    fun unsubscribeFromTransactions(listener: Listener)

    interface Listener {
        fun onNewEvent(event: TransactionResource<out Transaction>)
    }
}