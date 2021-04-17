package com.zm.blockchain_transactions.transactions

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.zm.blockchain_transactions.di.CoreDependencies
import com.zm.blockchain_transactions.transactions.databinding.TransactionsFragmentBinding
import com.zm.blockchain_transactions.transactions.di.DaggerTransactionsComponent
import com.zm.domain.util.TransactionResource
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
    private val adapter: TransactionsAdapter by lazy {
        TransactionsAdapter()
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
        viewModel.transactions.observe(viewLifecycleOwner) {
            when(it) {
                is TransactionResource.Connected -> {
                    binding.startButton.isEnabled = false
                    binding.stopButton.isEnabled = true
                }
                is TransactionResource.Disconnected -> {
                    binding.startButton.isEnabled = true
                    binding.stopButton.isEnabled = false
                }
                is TransactionResource.Failure -> {
                    Toast.makeText(requireContext(), it.e?.message?:"Error", Toast.LENGTH_SHORT).show()
                    binding.startButton.isEnabled = true
                    binding.stopButton.isEnabled = false
                }
                is TransactionResource.NewData -> {
                    adapter.addTransaction(it.data)
                }
            }
        }
    }

    private fun setupViews() {
        binding.startButton.setOnClickListener {
            viewModel.subscribeToTransactions()
            binding.startButton.isEnabled = false
            binding.stopButton.isEnabled = true
        }
        binding.stopButton.setOnClickListener {
            viewModel.unsubscribeFromTransactions()
        }
        binding.clearButton.setOnClickListener {
            adapter.clearItems()
        }

        binding.transactionsRV.layoutManager = LinearLayoutManager(requireContext())
        binding.transactionsRV.adapter = adapter
        binding.transactionsRV.itemAnimator = DefaultItemAnimator()
        binding.transactionsRV.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
    }



}