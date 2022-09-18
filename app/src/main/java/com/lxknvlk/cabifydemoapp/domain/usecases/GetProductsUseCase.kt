package com.lxknvlk.cabifydemoapp.domain.usecases

import com.lxknvlk.cabifydemoapp.data.api.ApiClient
import com.lxknvlk.cabifydemoapp.data.api.ProductResponse
import com.lxknvlk.cabifydemoapp.domain.entity.Product
import javax.inject.Inject


class GetProductsUseCase @Inject constructor(
    private val apiClient: ApiClient
) {
    suspend fun getProducts(): List<Product>?{
        val productResponse: ProductResponse? = apiClient.getProducts()
        return productResponse?.products
    }
}