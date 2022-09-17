package com.lxknvlk.cabifydemoapp.domain.usecase

import com.lxknvlk.cabifydemoapp.data.api.ApiClient
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val apiClient: ApiClient
) {
    suspend fun getProducts(): Any{
        return apiClient.getProducts()
    }
}