package com.lxknvlk.cabifydemoapp.domain.discounts

import com.lxknvlk.cabifydemoapp.domain.ProductEntity

interface Discount {
    fun applyDiscount(productList: List<ProductEntity>): List<ProductEntity>
}