/*
package com.example.exhibitapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.makeupapp.R
import com.example.makeupapp.databinding.ProductRecyclerviewBinding
import com.example.makeupapp.databinding.ProductRecyclerviewItemBinding


// Parent Adapter for the vertical scroll list of images
class ParentBrandAdapter: RecyclerView.Adapter<ParentBrandAdapter.ParentBrandAdapter>() {

    inner class ParentBrandAdapter(val binding: ProductRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root)

    */
/*private val differCallback = object: DiffUtil.ItemCallback<ExhibitItem>() {
        override fun areItemsTheSame(oldItem: ExhibitItem, newItem: ExhibitItem): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: ExhibitItem, newItem: ExhibitItem): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)*//*


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentBrandAdapter {
        return ParentBrandAdapter(ProductRecyclerviewBinding.inflate(LayoutInflater.from(parent.context),
            parent, false))
    }

    override fun getItemCount() = //differ.currentList.size

    override fun onBindViewHolder(holder: ParentBrandAdapter, position: Int) {
        //val brandItem = differ.currentList[position]
        //val childAdapter = ChildProductAdapter(exhibitItem.images as ArrayList<String>)
        holder.binding.apply {
            //tvTitle.text = exhibitItem.title
            rvChild.layoutManager = LinearLayoutManager(holder.binding.root.context, LinearLayoutManager.HORIZONTAL, false)
            //rvChild.adapter = childAdapter
        }
    }

}*/
