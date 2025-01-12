package com.mdf.test.samir.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.mdf.test.samir.R
import com.mdf.test.samir.databinding.ItemDocumentBinding
import com.mdf.test.samir.domain.model.Document
import com.mdf.test.samir.utils.Constants.BASE_URL

class DocumentAdapter(
    private val onItemClick: (Document) -> Unit,
) : RecyclerView.Adapter<DocumentAdapter.ItemViewHolder>() {

    private var items: MutableList<Document> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding =
            ItemDocumentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = items.size

    fun setItems(newItems: List<Document>) {
        items = newItems.toMutableList()
        notifyDataSetChanged()
    }

    inner class ItemViewHolder(private val binding: ItemDocumentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Document) {
            val circularProgressDrawable = CircularProgressDrawable(binding.root.context).apply {
                strokeWidth = 5f
                centerRadius = 15f
                setColorSchemeColors(
                    ContextCompat.getColor(
                        binding.root.context, R.color.indicator
                    )
                )
            }
            circularProgressDrawable.start()

            binding.apply {
                tvDocumentType.text = item.type
                Glide.with(binding.root.context).load(BASE_URL + item.url)
                    .placeholder(R.drawable.ic_placeholder).error(R.drawable.ic_placeholder_error)
                    .into(binding.ivDocumentPreview)

                root.setOnClickListener {
                    onItemClick(item)
                }
            }
        }
    }
}
