package com.zm.blockchain_transactions.transactions

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zm.domain.model.Transaction
import com.zm.domain.repository.TransactionsRepository
import com.zm.domain.usecase.TransactionsUseCase
import com.zm.domain.util.TransactionResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

class TransactionsViewModel @Inject constructor(
    private val transactionsUseCase: TransactionsUseCase
) : ViewModel() {
    private val transactionsLiveData = MutableLiveData<TransactionResource<out Transaction>>()
    val transactions: LiveData<TransactionResource<out Transaction>> = transactionsLiveData

    private val transactionsTotalLiveData = MutableLiveData<Long>()
    val transactionsTotalSize: LiveData<Long> = transactionsTotalLiveData

    private var total = 0L
    private val listener = object : TransactionsRepository.Listener {
        @Synchronized
        override fun onNewEvent(event: TransactionResource<out Transaction>) {
            if (event is TransactionResource.NewData) {
                total += event.data.x.size
                transactionsTotalLiveData.postValue(total)
                println("New event ${this.hashCode()}: ${event.data}")
            }
            viewModelScope.launch(Dispatchers.Main) {
                transactionsLiveData.value = event
            }
        }
    }

    fun subscribeToTransactions() {
        transactionsUseCase.subscribeToTransactions(listener)
    }

    fun unsubscribeFromTransactions() {
        transactionsUseCase.unsubscribeFromTransactions(listener)
    }
    fun clear() {
        total = 0
        transactionsTotalLiveData.value = total
    }

    override fun onCleared() {
        super.onCleared()
        transactionsUseCase.unsubscribeFromTransactions(listener)
        viewModelScope.cancel()
    }

}