package com.lxknvlk.cabifydemoapp.domain.discounts

import com.lxknvlk.cabifydemoapp.domain.entity.ProductVoucher

class VoucherDiscount: Discount<ProductVoucher> {
    /**
     *  for each 2 vouchers one of them is free
     */
    override fun applyDiscount(productList: List<ProductVoucher>): List<ProductVoucher>{
        val resultList = mutableListOf<ProductVoucher>()
        resultList.addAll(productList)

        val finalList: List<ProductVoucher> = resultList.mapIndexed { index, pe ->
            val newPrice = if (index % 2 == 1) 0.0 else pe.price
            ProductVoucher(pe.code, pe.name, newPrice)
        }

        return finalList
    }
}