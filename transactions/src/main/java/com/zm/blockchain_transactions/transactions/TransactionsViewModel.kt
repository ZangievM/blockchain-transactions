package com.zm.blockchain_transactions.transactions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zm.domain.model.Transaction
import com.zm.domain.repository.TransactionsRepository
import com.zm.domain.usecase.TransactionsUseCase
import com.zm.domain.util.TransactionResource
import javax.inject.Inject

class TransactionsViewModel @Inject constructor(
    private val transactionsUseCase: TransactionsUseCase
) : ViewModel() {
    private val transactionsLiveData = MutableLiveData<TransactionResource<out Transaction>>()
    val transactions: LiveData<TransactionResource<out Transaction>> = transactionsLiveData

    private val listener = object : TransactionsRepository.Listener {
        override fun onNewEvent(event: TransactionResource<out Transaction>) {
            transactionsLiveData.postValue(event)
        }
    }

    fun subscribeToTransactions() {
        transactionsUseCase.subscribeToTransactions(listener)
    }

    fun unsubscribeFromTransactions() {
        transactionsUseCase.unsubscribeFromTransactions(listener)
    }

}