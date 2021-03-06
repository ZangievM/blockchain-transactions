package com.zm.blockchain_transactions.transactions

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.zm.blockchain_transactions.R
import com.zm.blockchain_transactions.transactions.databinding.TransactionViewBinding
import com.zm.domain.model.Transaction
import java.text.SimpleDateFormat
import java.util.*

class TransactionsAdapter : RecyclerView.Adapter<TransactionsAdapter.ViewHolder>() {
    private val items = mutableListOf<Transaction>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            TransactionViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    @Synchronized
    fun addTransaction(transaction: Transaction) {
        items.add(transaction)
        notifyItemInserted(items.size - 1)
    }

    @Synchronized
    fun clearItems() {
        val size = items.size
        items.clear()
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: TransactionViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(transaction: Transaction) {
            binding.root.animation = AnimationUtils.loadAnimation(binding.root.context, R.anim.item_animation)
            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)
            binding.dateTextView.text = dateFormat.format(transaction.x.time * 1000)
            binding.sizeTextView.text = transaction.x.size.toString()
        }
    }
}