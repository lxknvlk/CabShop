package com.lxknvlk.cabifydemoapp.domain.discounts

import com.lxknvlk.cabifydemoapp.domain.ProductEntity

class MugDiscount: Discount {
    override fun applyDiscount(productList: List<ProductEntity>): List<ProductEntity> {
        // currently no mug discounts so returning same list
        return productList
    }
}