package com.lxknvlk.cabifydemoapp.domain.discounts

import com.lxknvlk.cabifydemoapp.domain.entity.Product
import com.lxknvlk.cabifydemoapp.domain.entity.ProductCode

class VoucherDiscount: Discount {
    override val productCode: ProductCode = ProductCode.VOUCHER
    override val productList: MutableList<Product> = mutableListOf()

    /**
     *  for each 2 vouchers one of them is free
     */
    override fun applyDiscount(){
        val finalList: List<Product> = productList.mapIndexed { index, pe ->
            val newPrice = if (index % 2 == 1) 0.0 else pe.price
            Product(pe.code, pe.name, newPrice)
        }

        productList.clear()
        productList.addAll(finalList)
    }
}