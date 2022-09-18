package com.lxknvlk.cabifydemoapp.domain

import com.lxknvlk.cabifydemoapp.domain.discounts.DiscountCalculator
import javax.inject.Inject

class CheckoutUseCase @Inject constructor(
    private val discountCalculator: DiscountCalculator,
    private val receiptCreator: ReceiptCreator
) {
    fun checkout(shoppingCart: ShoppingCart): String {
        val productList = shoppingCart.getCartContents()

        val finalProductsList = discountCalculator.applyDiscounts(productList)

        return receiptCreator.createReceipt(finalProductsList)
    }
}