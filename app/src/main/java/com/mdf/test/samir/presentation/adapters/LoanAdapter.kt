package com.mdf.test.samir.presentation.adapters

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.mdf.test.samir.R.color
import com.mdf.test.samir.R.string
import com.mdf.test.samir.databinding.ItemLoanBinding
import com.mdf.test.samir.domain.model.Loan
import com.mdf.test.samir.utils.toUsd
import java.util.Locale

class LoanAdapter(
    private val onItemClick: (Loan) -> Unit
) : RecyclerView.Adapter<LoanAdapter.ItemViewHolder>() {

    private val loanList = mutableListOf<Loan>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemLoanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = loanList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = loanList.size

    fun setData(newList: List<Loan>) {
        loanList.clear()
        loanList.addAll(newList)
        notifyDataSetChanged()
    }

    inner class ItemViewHolder(private val binding: ItemLoanBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Loan) {
            binding.apply {
                tvBorrowerName.text = item.borrower.name
                tvLoanAmount.text = item.amount.toUsd()

                tvInterestRate.text = item.interestRate?.let {
                    String.format(Locale.getDefault(), "%.1f%%", it)
                } ?: "-"

                tvTerm.text = binding.root.context.getString(string.loan_term_format, item.term)

                tvPurpose.text = item.purpose

                tvRiskRating.apply {
                    text = item.riskRating
                    typeface = Typeface.DEFAULT_BOLD
                    setChipBackgroundColorResource(
                        when (item.riskRating.uppercase()) {
                            "A" -> color.green_100
                            "B" -> color.blue_100
                            "C" -> color.yellow_100
                            else -> color.red_100
                        }
                    )
                    setTextColor(ContextCompat.getColor(binding.root.context, color.black))
                }

                root.setOnClickListener {
                    onItemClick(item)
                }
            }
        }
    }
}
