package com.example.makeupapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.makeupapp.databinding.FragmentMakeupProductBinding
import com.example.makeupapp.viewmodel.MakeupViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class MakeupProductFragment : Fragment() {

    private var _binding: FragmentMakeupProductBinding? = null
    private val binding get() = _binding!!
    val arg: MakeupProductFragmentArgs by navArgs()


    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMakeupProductBinding.inflate(inflater, container, false)
        val view = binding.root

        val product = arg.productListItem

        // Print message if data is transferred
        product?.brand.let { Timber.tag("MakeupProduct").d("Success") }

        // Attach product data to views
        binding.apply {
            Picasso.get().load(product?.image_link).into(binding.ivProductImage)
            tvBrandName.text = product?.brand
            tvProductName.text = product?.name
            tvPrice.text = "$"+product?.price
            tvDescription.text = product?.description
        }

        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}