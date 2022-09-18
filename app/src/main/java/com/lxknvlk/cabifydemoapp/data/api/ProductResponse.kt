package com.lxknvlk.cabifydemoapp.data.api

import com.lxknvlk.cabifydemoapp.domain.ProductEntity

data class ProductResponse(
    var products: List<ProductEntity>
)