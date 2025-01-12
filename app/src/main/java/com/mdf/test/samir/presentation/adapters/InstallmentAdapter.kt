package com.mdf.test.samir.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mdf.test.samir.databinding.ItemInstallmentBinding
import com.mdf.test.samir.domain.model.Installment
import com.mdf.test.samir.utils.toFormattedDate
import com.mdf.test.samir.utils.toUsd

class InstallmentAdapter : RecyclerView.Adapter<InstallmentAdapter.ItemViewHolder>() {

    private var items: MutableList<Installment> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding =
            ItemInstallmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = items.size

    fun setItems(newItems: List<Installment>) {
        items = newItems.toMutableList()
        notifyDataSetChanged()
    }

    inner class ItemViewHolder(private val binding: ItemInstallmentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Installment) {
            binding.apply {
                tvDueDate.text = item.dueDate.toFormattedDate()
                tvAmountDue.text = item.amountDue.toUsd()
            }
        }
    }
}
