package com.example.makeupapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.makeupapp.databinding.ProductRecyclerviewItemBinding
import com.squareup.picasso.Picasso

//Child Adapter for the horizontal scroll list of images
class ChildProductAdapter(private var productList: ArrayList<String>) : RecyclerView.Adapter<ChildProductAdapter.ChildViewHolder>() {

    inner class ChildViewHolder(val binding: ProductRecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {
        return ChildViewHolder(ProductRecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context),
            parent, false))
    }

    override fun getItemCount() = productList.count()

    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {
        val productItem = productList[position]
        holder.binding.apply {
            Picasso.get().load(productItem).into(ivProductImage)
        }
    }

}