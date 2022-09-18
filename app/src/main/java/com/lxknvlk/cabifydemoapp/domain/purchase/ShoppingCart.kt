package com.lxknvlk.cabifydemoapp.domain.purchase

import com.lxknvlk.cabifydemoapp.domain.ProductEntity

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

    fun addProducts(newProducts: List<ProductEntity>){
        products.clear()
        products.addAll(newProducts)
    }
}