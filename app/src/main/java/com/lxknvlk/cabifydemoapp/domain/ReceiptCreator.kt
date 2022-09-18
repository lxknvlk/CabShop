package com.lxknvlk.cabifydemoapp.domain

class ReceiptCreator {
    fun createReceipt(productList: List<ProductEntity>): String{

        var receiptString = ""
        var totalPrice = 0.0

        productList.forEach {
            receiptString += "${it.name}  €${it.price}\n"
            totalPrice += it.price
        }

        receiptString += "\nTotal: €$totalPrice"

        return receiptString
    }
}