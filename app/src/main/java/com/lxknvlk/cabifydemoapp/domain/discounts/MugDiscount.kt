package com.lxknvlk.cabifydemoapp.domain.discounts

import com.lxknvlk.cabifydemoapp.domain.entity.ProductMug

class MugDiscount: Discount<ProductMug> {
    override fun applyDiscount(productList: List<ProductMug>): List<ProductMug> {
        // currently no mug discounts so returning same list
        return productList
    }
}