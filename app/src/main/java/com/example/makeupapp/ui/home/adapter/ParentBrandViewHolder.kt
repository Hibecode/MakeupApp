package com.example.makeupapp.ui.home.adapter

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.makeupapp.data.model.GroupedProductsList
import com.example.makeupapp.data.model.ProductsListItem
import com.example.makeupapp.databinding.ProductRecyclerviewBinding

class ParentBrandViewHolder(
    private val binding: ProductRecyclerviewBinding,
    private val listener: (ProductsListItem) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(productItem: GroupedProductsList) {
        val childAdapter =
            ChildProductAdapter(productItem.productsLists as ArrayList<ProductsListItem>) {
                listener.invoke(it)
            }

        binding.apply {
            tvBrandname.text = productItem.brand
            rvChild.layoutManager = LinearLayoutManager(
                itemView.context, LinearLayoutManager.HORIZONTAL, false
            )
            rvChild.adapter = childAdapter
        }
    }
}