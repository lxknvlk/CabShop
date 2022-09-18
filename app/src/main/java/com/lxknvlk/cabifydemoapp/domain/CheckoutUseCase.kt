package com.lxknvlk.cabifydemoapp.domain

import com.lxknvlk.cabifydemoapp.domain.discounts.MugDiscount
import com.lxknvlk.cabifydemoapp.domain.discounts.TShirtDiscount
import com.lxknvlk.cabifydemoapp.domain.discounts.VoucherDiscount

class CheckoutUseCase {
    fun checkout(shoppingCart: ShoppingCart): String {

        val voucherDiscount = VoucherDiscount()
        val tShirtDiscount = TShirtDiscount()
        val mugDiscount = MugDiscount()

        val contents = shoppingCart.getCartContents()

        var receiptString = ""
        var totalPrice: Double = 0.0

        val vouchers = mutableListOf<ProductEntity>()
        val mugs = mutableListOf<ProductEntity>()
        val tshirts = mutableListOf<ProductEntity>()

        contents.forEach {
            when (it.code) {
                "VOUCHER" -> vouchers.add(it)
                "MUG" -> mugs.add(it)
                "TSHIRT" -> tshirts.add(it)
            }
        }

        val finalVouchers = voucherDiscount.applyDiscount(vouchers)
        val finalTShirts = tShirtDiscount.applyDiscount(tshirts)
        val finalMugs = mugDiscount.applyDiscount(mugs)

        val finalProducts = mutableListOf<ProductEntity>()
        finalProducts.addAll(finalVouchers)
        finalProducts.addAll(finalTShirts)
        finalProducts.addAll(finalMugs)

        finalProducts.forEach {
            receiptString += "${it.name}  €${it.price}\n"
            totalPrice += it.price
        }

        receiptString += "\nTotal: €$totalPrice"

        return receiptString
    }
}