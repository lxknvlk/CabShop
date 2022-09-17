package com.lxknvlk.cabifydemoapp.domain.usecase

import com.lxknvlk.cabifydemoapp.data.api.ApiClient

class GetProductsUseCase {
    private val apiClient = ApiClient()

    suspend fun getProducts(): Any{
        return apiClient.getProducts()
    }
}