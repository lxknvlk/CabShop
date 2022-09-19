package com.lxknvlk.cabifydemoapp.domain.discounts

import com.lxknvlk.cabifydemoapp.domain.entity.*
import javax.inject.Inject

class DiscountCalculator @Inject constructor(
    private val voucherDiscount: VoucherDiscount,
    private val tShirtDiscount: TShirtDiscount,
    private val mugDiscount: MugDiscount
) {
    fun applyDiscounts(shoppingCart: ShoppingCart): ShoppingCart {
        val productList = shoppingCart.getCartContents()

        val vouchers = mutableListOf<ProductVoucher>()
        val mugs = mutableListOf<ProductMug>()
        val tshirts = mutableListOf<ProductTShirt>()

        productList.forEach {
            when (it) {
                is ProductVoucher -> vouchers.add(it)
                is ProductMug -> mugs.add(it)
                is ProductTShirt -> tshirts.add(it)
            }
        }

        val finalVouchers = voucherDiscount.applyDiscount(vouchers)
        val finalTShirts = tShirtDiscount.applyDiscount(tshirts)
        val finalMugs = mugDiscount.applyDiscount(mugs)

        val finalProducts = mutableListOf<Product>()
        finalProducts.addAll(finalVouchers)
        finalProducts.addAll(finalTShirts)
        finalProducts.addAll(finalMugs)

        val finalShoppingCart = ShoppingCart()
        finalShoppingCart.addProducts(finalProducts)

        return finalShoppingCart
    }
}