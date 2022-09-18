package com.lxknvlk.cabifydemoapp.domain.discounts

import com.lxknvlk.cabifydemoapp.domain.ProductEntity

class TShirtDiscount: Discount {

    /**
     *  If there are 3 or more tshirts, set price of all to 19.00
     */
    override fun applyDiscount(productList: List<ProductEntity>): List<ProductEntity> {
        if (productList.size >= 3){
            return productList.map { it -> ProductEntity(it.code, it.name, 19.00) }
        } else {
            return productList
        }
    }
}