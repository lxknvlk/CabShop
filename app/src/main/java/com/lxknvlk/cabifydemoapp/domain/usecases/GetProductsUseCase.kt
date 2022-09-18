package com.lxknvlk.cabifydemoapp.domain.usecases

import com.lxknvlk.cabifydemoapp.domain.entity.Product
import com.lxknvlk.cabifydemoapp.domain.interfaces.ProductRepositoryRemote
import javax.inject.Inject


class GetProductsUseCase @Inject constructor(
    private val productRepositoryRemote: ProductRepositoryRemote
) {
    suspend fun getProducts(): List<Product>? {
        return productRepositoryRemote.getProducts()
    }
}