package com.lxknvlk.cabifydemoapp.domain.usecases

import com.lxknvlk.cabifydemoapp.domain.discounts.DiscountCalculator
import com.lxknvlk.cabifydemoapp.domain.entity.Receipt
import com.lxknvlk.cabifydemoapp.domain.utils.ReceiptCreator
import com.lxknvlk.cabifydemoapp.domain.entity.ShoppingCart
import javax.inject.Inject

class CheckoutUseCase @Inject constructor(
    private val discountCalculator: DiscountCalculator,
    private val receiptCreator: ReceiptCreator
) {
    fun checkout(shoppingCart: ShoppingCart): Receipt {

        val finalShoppingCart = discountCalculator.applyDiscounts(shoppingCart)

        return receiptCreator.createReceipt(finalShoppingCart)
    }
}