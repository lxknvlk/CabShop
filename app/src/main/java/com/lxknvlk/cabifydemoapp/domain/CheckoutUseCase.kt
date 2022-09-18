package com.lxknvlk.cabifydemoapp.domain

import com.lxknvlk.cabifydemoapp.domain.discounts.DiscountCalculator
import com.lxknvlk.cabifydemoapp.domain.purchase.ReceiptCreator
import com.lxknvlk.cabifydemoapp.domain.purchase.ShoppingCart
import javax.inject.Inject

class CheckoutUseCase @Inject constructor(
    private val discountCalculator: DiscountCalculator,
    private val receiptCreator: ReceiptCreator
) {
    fun checkout(shoppingCart: ShoppingCart): String {

        val finalShoppingCart = discountCalculator.applyDiscounts(shoppingCart)

        val receipt = receiptCreator.createReceipt(finalShoppingCart)

        return receipt.orderText
    }
}