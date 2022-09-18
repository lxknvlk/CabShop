package com.lxknvlk.cabifydemoapp.domain.discounts

import com.lxknvlk.cabifydemoapp.domain.ProductEntity

class VoucherDiscount: Discount {
    /**
     *  for each 2 vouchers one of them is free
     */
    override fun applyDiscount(productList: List<ProductEntity>): List<ProductEntity>{
        val resultList = mutableListOf<ProductEntity>()
        resultList.addAll(productList)

        val finalList: List<ProductEntity> = resultList.mapIndexed { index, pe ->
            val newPrice = if (index % 2 == 1) 0.0 else pe.price
            ProductEntity(pe.code, pe.name, newPrice)
        }

        return finalList
    }
}