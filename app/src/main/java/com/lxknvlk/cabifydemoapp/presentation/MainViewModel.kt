package com.lxknvlk.cabifydemoapp.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lxknvlk.cabifydemoapp.domain.usecase.GetProductsUseCase

class MainViewModel : ViewModel() {
    private val getProductsUseCase = GetProductsUseCase()

    val productsLiveData = MutableLiveData<Any>()

    suspend fun getProducts(){
        val products = getProductsUseCase.getProducts()

        productsLiveData.postValue(products)
    }
}