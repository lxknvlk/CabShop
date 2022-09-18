package com.lxknvlk.cabifydemoapp.domain.purchase

class ReceiptCreator {
    fun createReceipt(shoppingCart: ShoppingCart): ReceiptEntity {

        val productList = shoppingCart.getCartContents()

        var receiptString = ""
        var totalPrice = 0.0

        productList.forEach {
            receiptString += "${it.name}  €${it.price}\n"
            totalPrice += it.price
        }

        receiptString += "\nTotal: €$totalPrice"

        return ReceiptEntity(shoppingCart, receiptString, totalPrice)
    }
}