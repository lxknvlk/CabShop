package com.lxknvlk.cabifydemoapp.data.api

class ApiClient {

    private val retrofitClient = RetrofitClient()
    private val apiInterface: ApiInterface = retrofitClient.getInstance().create(ApiInterface::class.java)

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