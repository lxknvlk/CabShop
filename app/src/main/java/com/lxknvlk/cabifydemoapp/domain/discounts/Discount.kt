package com.lxknvlk.cabifydemoapp.domain.discounts

import com.lxknvlk.cabifydemoapp.domain.entity.Product

interface Discount {
    fun applyDiscount(productList: List<Product>): List<Product>
}