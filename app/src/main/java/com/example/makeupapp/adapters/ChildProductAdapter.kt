package com.example.makeupapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.makeupapp.R
import com.example.makeupapp.databinding.ProductRecyclerviewItemBinding
import com.example.makeupapp.models.ProductsListItem
import com.squareup.picasso.Picasso
import timber.log.Timber


//Child Adapter for the horizontal scroll list of images
class ChildProductAdapter(private var productList: ArrayList<ProductsListItem>) : RecyclerView.Adapter<ChildProductAdapter.ChildViewHolder>() {

    inner class ChildViewHolder(val binding: ProductRecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {
        return ChildViewHolder(ProductRecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context),
            parent, false))
    }

    override fun getItemCount() = productList.count()

    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {
        val productItem = productList[position]
        holder.binding.apply {
            tvBrandName.text = productItem.brand
            tvProductName.text = productItem.name
            Picasso.get().load(productItem.image_link).into(ivProductImage)

            //Once the item view is clicked the item product data is passed to the
            //onItemClickListener. Continue down
            root.setOnClickListener{
                Timber.tag("ChildProductAdapter").d("Checking..")
                onItemClickListener?.let { it(productItem) }
            }
        }
    }

    //This is used to store the item product data
    private var onItemClickListener: ((ProductsListItem) -> Unit)? = null

    //This function takes whatever is called on it in the fragment(which is the listener)
    //and assigns it to the onItemClickListener (which later gets the product parameter)
    fun setOnItemClickListener(listener: ((ProductsListItem) -> Unit)) {
        onItemClickListener = listener
    }


}