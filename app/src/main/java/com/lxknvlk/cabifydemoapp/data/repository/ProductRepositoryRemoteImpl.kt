package com.lxknvlk.cabifydemoapp.data.repository

import com.lxknvlk.cabifydemoapp.data.ProductEntityMapper
import com.lxknvlk.cabifydemoapp.data.api.ApiClient
import com.lxknvlk.cabifydemoapp.data.api.ProductResponse
import com.lxknvlk.cabifydemoapp.domain.entity.Product
import com.lxknvlk.cabifydemoapp.domain.interfaces.ProductRepositoryRemote
import javax.inject.Inject

class ProductRepositoryRemoteImpl @Inject constructor(
    private val apiClient: ApiClient,
    private val productEntityMapper: ProductEntityMapper
) : ProductRepositoryRemote {
    override suspend fun getProducts(): List<Product> {
        val productResponse: ProductResponse? = apiClient.getProducts()

        val productDTOList = productResponse?.products ?: listOf()

        return productEntityMapper.mapDTOToEntities(productDTOList)
    }
}