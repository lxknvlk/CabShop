package com.lxknvlk.cabifydemoapp.domain.utils

import com.lxknvlk.cabifydemoapp.domain.entity.Receipt
import com.lxknvlk.cabifydemoapp.domain.entity.ShoppingCart

class ReceiptCreator {
    fun createReceipt(shoppingCart: ShoppingCart): Receipt {

        val productList = shoppingCart.getCartContents()

        var receiptString = ""
        var totalPrice = 0.0

        productList.forEach {
            receiptString += "${it.name}  €${it.price}\n"
            totalPrice += it.price
        }

        receiptString += "\nTotal: €$totalPrice"

        return Receipt(shoppingCart, receiptString, totalPrice)
    }
}