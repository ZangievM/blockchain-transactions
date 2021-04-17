package com.zm.domain.usecase

import com.zm.domain.repository.TransactionsRepository
import com.zm.domain.util.TransactionResource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TransactionsUseCase @Inject constructor(
    private val transactionsRepository: TransactionsRepository
) {
    fun subscribeToTransactions(listener: TransactionsRepository.Listener) {
        return transactionsRepository.subscribeToTransactions(listener)
    }
    fun unsubscribeFromTransactions(listener: TransactionsRepository.Listener) {
        transactionsRepository.unsubscribeFromTransactions(listener)
    }
}