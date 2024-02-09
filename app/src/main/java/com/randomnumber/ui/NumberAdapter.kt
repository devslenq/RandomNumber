package com.randomnumber.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.randomnumber.core.base.extensions.onSingleClick
import com.randomnumber.core.data.network.models.ItemNumberModel
import com.randomnumber.databinding.ItemNumberBinding

class NumberAdapter(private var list: List<ItemNumberModel> = listOf(), private val onClickItem: (ItemNumberModel) -> Unit) :
    RecyclerView.Adapter<NumberAdapter.NumberViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNumberBinding.inflate(inflater, parent, false)

        return NumberViewHolder(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: NumberViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class NumberViewHolder(val binding: ItemNumberBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemNumberModel) {
            binding.numberInfo.text = item.text
            binding.number.text = item.number.toString()
            binding.root.onSingleClick {
                onClickItem.invoke(item)
            }
        }
    }

    fun putItems(list: List<ItemNumberModel>) {
        this.list = list
        notifyDataSetChanged()
    }

    fun putItem(item: ItemNumberModel) {
        val newItems = list.toMutableList()
        newItems.add(0, item)
        list = newItems
        notifyDataSetChanged()
    }
}