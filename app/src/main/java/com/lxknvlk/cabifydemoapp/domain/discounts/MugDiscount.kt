package com.lxknvlk.cabifydemoapp.domain.discounts

import com.lxknvlk.cabifydemoapp.domain.entity.Product
import com.lxknvlk.cabifydemoapp.domain.entity.ProductCode

class MugDiscount: Discount {
    override val productCode: ProductCode = ProductCode.MUG
    override val productList: MutableList<Product> = mutableListOf()

    override fun applyDiscount() {
        //no discount for mugs
    }
}