package com.lxknvlk.cabifydemoapp.domain.discounts

import com.lxknvlk.cabifydemoapp.domain.entity.Product

class VoucherDiscount: Discount {
    /**
     *  for each 2 vouchers one of them is free
     */
    override fun applyDiscount(productList: List<Product>): List<Product>{
        val resultList = mutableListOf<Product>()
        resultList.addAll(productList)

        val finalList: List<Product> = resultList.mapIndexed { index, pe ->
            val newPrice = if (index % 2 == 1) 0.0 else pe.price
            Product(pe.code, pe.name, newPrice)
        }

        return finalList
    }
}