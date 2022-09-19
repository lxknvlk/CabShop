package com.lxknvlk.cabifydemoapp.domain.discounts

import com.lxknvlk.cabifydemoapp.domain.entity.ProductTShirt

class TShirtDiscount: Discount<ProductTShirt> {

    /**
     *  If there are 3 or more tshirts, set price of all to 19.00
     */
    override fun applyDiscount(productList: List<ProductTShirt>): List<ProductTShirt> {
        if (productList.size >= 3){
            return productList.map { it -> ProductTShirt(it.code, it.name, 19.00) }
        } else {
            return productList
        }
    }
}