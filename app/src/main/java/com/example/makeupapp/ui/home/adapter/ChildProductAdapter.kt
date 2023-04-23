package com.example.makeupapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.makeupapp.data.model.ProductsListItem
import com.example.makeupapp.databinding.ProductRecyclerviewItemBinding

//Child Adapter for the horizontal scroll list of images
class ChildProductAdapter(private var productList: ArrayList<ProductsListItem>,
                          private val listener : (ProductsListItem) -> Unit) :
    RecyclerView.Adapter<ChildProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildProductViewHolder {
        return ChildProductViewHolder(
            ProductRecyclerviewItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ),
            listener
        )
    }

    override fun getItemCount() = productList.count()

    override fun onBindViewHolder(holder: ChildProductViewHolder, position: Int) {
        holder.bind(productList[position])
    }
}