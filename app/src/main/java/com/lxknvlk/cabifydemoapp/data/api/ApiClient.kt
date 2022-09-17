package com.lxknvlk.cabifydemoapp.data.api

import javax.inject.Inject

class ApiClient  @Inject constructor(
    private val apiInterface: ApiInterface
) {

    suspend fun getProducts(): Any {
        try {
            val response = apiInterface.getProducts()

            if (response.isSuccessful && response.body() != null) {
                return response.body()!!
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return "nope"
    }
}