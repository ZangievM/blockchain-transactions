package com.zm.blockchain_transactions.transactions

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zm.blockchain_transactions.di.CoreDependencies
import com.zm.blockchain_transactions.transactions.databinding.TransactionsFragmentBinding
import com.zm.blockchain_transactions.transactions.di.DaggerTransactionsComponent
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class TransactionsFragment : Fragment() {

    companion object {
        fun newInstance() = TransactionsFragment()
    }

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel: TransactionsViewModel by lazy {
        ViewModelProvider(this, factory).get(TransactionsViewModel::class.java)
    }

    private lateinit var binding: TransactionsFragmentBinding


    override fun onAttach(context: Context) {
        super.onAttach(context)
        inject()
    }

    private fun inject() {
        DaggerTransactionsComponent
            .builder()
            .context(requireContext())
            .dependencies(
                EntryPointAccessors.fromApplication(
                    requireContext(),
                    CoreDependencies::class.java
                )
            ).build()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            TransactionsFragmentBinding.inflate(inflater, container, false)
        setupViews()
        setupObservers()
        return binding.root
    }

    private fun setupObservers() {

    }

    private fun setupViews() {

    }



}