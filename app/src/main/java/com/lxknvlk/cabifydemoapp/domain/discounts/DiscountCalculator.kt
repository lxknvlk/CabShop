package com.lxknvlk.cabifydemoapp.domain.discounts

import com.lxknvlk.cabifydemoapp.domain.entity.Product
import com.lxknvlk.cabifydemoapp.domain.entity.ProductCode
import com.lxknvlk.cabifydemoapp.domain.entity.ShoppingCart
import javax.inject.Inject

class DiscountCalculator @Inject constructor(
    private val voucherDiscount: VoucherDiscount,
    private val tShirtDiscount: TShirtDiscount,
    private val mugDiscount: MugDiscount
) {
    fun applyDiscounts(shoppingCart: ShoppingCart): ShoppingCart {
        val productList = shoppingCart.getCartContents()

        val vouchers = mutableListOf<Product>()
        val mugs = mutableListOf<Product>()
        val tshirts = mutableListOf<Product>()

        productList.forEach {
            when (it.code) {
                ProductCode.VOUCHER -> vouchers.add(it)
                ProductCode.MUG -> mugs.add(it)
                ProductCode.TSHIRT -> tshirts.add(it)
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