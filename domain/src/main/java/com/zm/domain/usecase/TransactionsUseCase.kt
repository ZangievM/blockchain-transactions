package com.zm.domain.usecase

import com.zm.domain.repository.TransactionsRepository
import com.zm.domain.util.TransactionResource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TransactionsUseCase @Inject constructor(
    private val transactionsRepository: TransactionsRepository
) {
    fun subscribeToTransactions(): Flow<TransactionResource<out String>> {
        return transactionsRepository.subscribeToTransactions()
    }
    fun unsubscribeFromTransactions() {
        transactionsRepository.unsubscribeFromTransactions()
    }
    fun connect() {
        transactionsRepository.connect()
    }
    fun disconnect() {
        transactionsRepository.disconnect()
    }
}