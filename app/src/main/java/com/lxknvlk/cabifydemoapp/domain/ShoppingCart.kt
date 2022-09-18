package com.lxknvlk.cabifydemoapp.domain

class ShoppingCart {
    private val products = mutableListOf<ProductEntity>()

    fun addProduct(product: ProductEntity){
        products.add(product)
    }

    fun removeProduct(product: ProductEntity){
        products.remove(product)
    }

    fun getCartContents(): List<ProductEntity>{
        return products
    }
}