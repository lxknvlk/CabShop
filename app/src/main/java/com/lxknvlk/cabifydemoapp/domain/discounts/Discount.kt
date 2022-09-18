package com.lxknvlk.cabifydemoapp.domain.discounts

import com.lxknvlk.cabifydemoapp.domain.entity.Product
import com.lxknvlk.cabifydemoapp.domain.entity.ProductCode

interface Discount {
    val productCode: ProductCode
    val productList: MutableList<Product>

    fun applyDiscount()

    fun addProduct(product: Product) {
        productList.add(product)
    }
}