package com.example.makeupapp.models

// Data model class for products with the same brand grouped together
data class GroupedProductsList(
    val brand: String?,
    val productsLists: List<ProductsListItem>
    )