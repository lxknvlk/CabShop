package com.lxknvlk.cabifydemoapp.domain

import com.lxknvlk.cabifydemoapp.data.api.ApiClient
import com.lxknvlk.cabifydemoapp.data.api.ProductResponse
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val apiClient: ApiClient
) {
    suspend fun getProducts(): List<ProductEntity>?{
        val productResponse: ProductResponse? = apiClient.getProducts()
        return productResponse?.products
    }
}