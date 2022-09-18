package com.lxknvlk.cabifydemoapp.domain.interfaces

import com.lxknvlk.cabifydemoapp.domain.entity.Product

interface ProductRepositoryRemote {
    suspend fun getProducts(): List<Product>?
}