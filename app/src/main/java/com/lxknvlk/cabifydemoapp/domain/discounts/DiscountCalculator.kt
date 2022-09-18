package com.lxknvlk.cabifydemoapp.domain.discounts

import com.lxknvlk.cabifydemoapp.domain.entity.Product
import com.lxknvlk.cabifydemoapp.domain.entity.ProductCode
import com.lxknvlk.cabifydemoapp.domain.entity.ShoppingCart
import javax.inject.Inject

class DiscountCalculator{
    fun applyDiscounts(shoppingCart: ShoppingCart): ShoppingCart {
        val voucherDiscount = VoucherDiscount()
        val tShirtDiscount = TShirtDiscount()
        val mugDiscount = MugDiscount()

        val productList = shoppingCart.getCartContents()

        productList.forEach {
            when (it.code) {
                ProductCode.VOUCHER -> voucherDiscount.addProduct(it)
                ProductCode.MUG -> mugDiscount.addProduct(it)
                ProductCode.TSHIRT -> tShirtDiscount.addProduct(it)
            }
        }

        voucherDiscount.applyDiscount()
        tShirtDiscount.applyDiscount()
        mugDiscount.applyDiscount()

        val finalProducts = mutableListOf<Product>()
        finalProducts.addAll(voucherDiscount.productList)
        finalProducts.addAll(tShirtDiscount.productList)
        finalProducts.addAll(mugDiscount.productList)

        val finalShoppingCart = ShoppingCart()
        finalShoppingCart.addProducts(finalProducts)

        return finalShoppingCart
    }
}