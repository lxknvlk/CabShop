package com.lxknvlk.cabifydemoapp.domain.discounts

import com.lxknvlk.cabifydemoapp.domain.entity.Product

class MugDiscount: Discount {
    override fun applyDiscount(productList: List<Product>): List<Product> {
        // currently no mug discounts so returning same list
        return productList
    }
}