package com.lxknvlk.cabifydemoapp.domain.discounts

import com.lxknvlk.cabifydemoapp.domain.entity.Product
import com.lxknvlk.cabifydemoapp.domain.entity.ProductCode

class TShirtDiscount: Discount {
    override val productCode: ProductCode = ProductCode.TSHIRT
    override val productList: MutableList<Product> = mutableListOf()

    /**
     *  If there are 3 or more tshirts, set price of all to 19.00
     */
    override fun applyDiscount() {
        val tempProductList = mutableListOf<Product>()
        tempProductList.addAll(productList)

        val finalList = tempProductList.map {
            val price = if (productList.size >= 3) 19.00 else it.price
            Product(it.code, it.name, price)
        }

        productList.clear()
        productList.addAll(finalList)
    }
}