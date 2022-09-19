package com.lxknvlk.cabifydemoapp.domain.discounts

interface Discount<T> {
    fun applyDiscount(productList: List<T>): List<T>
}