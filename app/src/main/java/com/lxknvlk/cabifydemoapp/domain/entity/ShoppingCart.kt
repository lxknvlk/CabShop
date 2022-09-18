package com.lxknvlk.cabifydemoapp.domain.entity

class ShoppingCart {
    private val products = mutableListOf<Product>()

    fun addProduct(product: Product){
        products.add(product)
    }

    fun removeProduct(product: Product){
        products.remove(product)
    }

    fun getCartContents(): List<Product>{
        return products
    }

    fun addProducts(newProducts: List<Product>){
        products.clear()
        products.addAll(newProducts)
    }
}