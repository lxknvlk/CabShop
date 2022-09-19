package com.lxknvlk.cabifydemoapp.data.api

import com.lxknvlk.cabifydemoapp.domain.entity.ProductCode

data class ProductDTO(
    var code: ProductCode,
    var name: String,
    var price: Double
)