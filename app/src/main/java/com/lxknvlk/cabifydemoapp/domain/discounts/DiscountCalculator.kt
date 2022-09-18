package com.lxknvlk.cabifydemoapp.domain.discounts

import com.lxknvlk.cabifydemoapp.domain.ProductEntity
import com.lxknvlk.cabifydemoapp.domain.purchase.ShoppingCart
import javax.inject.Inject

class DiscountCalculator @Inject constructor(
    private val voucherDiscount: VoucherDiscount,
    private val tShirtDiscount: TShirtDiscount,
    private val mugDiscount: MugDiscount
) {
    fun applyDiscounts(shoppingCart: ShoppingCart): ShoppingCart {
        val productList = shoppingCart.getCartContents()

        val vouchers = mutableListOf<ProductEntity>()
        val mugs = mutableListOf<ProductEntity>()
        val tshirts = mutableListOf<ProductEntity>()

        productList.forEach {
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

        val finalShoppingCart = ShoppingCart()
        finalShoppingCart.addProducts(finalProducts)

        return finalShoppingCart
    }
}