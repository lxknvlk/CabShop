package com.lxknvlk.cabifydemoapp.domain.entity

data class Receipt(
    var shoppingCart: ShoppingCart,
    var orderText: String,
    var totalPrice: Double
)