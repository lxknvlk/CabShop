package com.lxknvlk.cabifydemoapp.domain.purchase

data class ReceiptEntity(
    var shoppingCart: ShoppingCart,
    var orderText: String,
    var totalPrice: Double
)