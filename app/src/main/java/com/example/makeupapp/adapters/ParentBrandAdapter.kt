package com.example.makeupapp.adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.makeupapp.HomeFragmentDirections
import com.example.makeupapp.R
import com.example.makeupapp.databinding.ProductRecyclerviewBinding
import com.example.makeupapp.databinding.ProductRecyclerviewItemBinding
import com.example.makeupapp.models.GroupedProductsList
import com.example.makeupapp.models.ProductsListItem
import timber.log.Timber


// Parent Adapter for the vertical scroll list of images
class ParentBrandAdapter: RecyclerView.Adapter<ParentBrandAdapter.ParentBrandAdapter>() {

    inner class ParentBrandAdapter(val binding: ProductRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root)

private val differCallback = object: DiffUtil.ItemCallback<GroupedProductsList>() {
        override fun areItemsTheSame(oldItem: GroupedProductsList, newItem: GroupedProductsList): Boolean {
            return oldItem.brand == newItem.brand
        }

        override fun areContentsTheSame(oldItem: GroupedProductsList, newItem: GroupedProductsList): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentBrandAdapter {
        return ParentBrandAdapter(ProductRecyclerviewBinding.inflate(LayoutInflater.from(parent.context),
            parent, false))
    }

    override fun getItemCount() = differ.currentList.size

    override fun onBindViewHolder(holder: ParentBrandAdapter, position: Int) {
        val productItem = differ.currentList[position]
        val childAdapter = ChildProductAdapter(productItem.productsLists as ArrayList<ProductsListItem>)
        holder.binding.apply {
            tvBrandname.text = productItem.brand
            rvChild.layoutManager = LinearLayoutManager(holder.binding.root.context, LinearLayoutManager.HORIZONTAL, false)
            rvChild.adapter = childAdapter

            childAdapter.setOnItemClickListener { product ->

                Timber.tag("Adapters").d("checking...")
                val action = HomeFragmentDirections.actionHomeFragmentToMakeupProductFragment(product)

                root.findNavController().navigate(action)
            }
        }
    }

}
