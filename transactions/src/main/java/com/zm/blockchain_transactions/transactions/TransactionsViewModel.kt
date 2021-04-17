package com.zm.blockchain_transactions.transactions

import androidx.lifecycle.ViewModel
import com.zm.domain.usecase.TransactionsUseCase
import javax.inject.Inject

class TransactionsViewModel @Inject constructor(
    private val transactionsUseCase: TransactionsUseCase
) : ViewModel() {

}