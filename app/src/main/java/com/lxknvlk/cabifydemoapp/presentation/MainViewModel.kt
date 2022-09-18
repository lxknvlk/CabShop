package com.lxknvlk.cabifydemoapp.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lxknvlk.cabifydemoapp.data.api.ProductResponse
import com.lxknvlk.cabifydemoapp.domain.GetProductsUseCase
import com.lxknvlk.cabifydemoapp.domain.ProductEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val coroutineDispatcherIO: CoroutineDispatcher
) : ViewModel() {
    val productsLiveData = MutableLiveData<List<ProductEntity>?>()

    fun getProducts(){
        viewModelScope.launch(coroutineDispatcherIO) {
            val products: List<ProductEntity>? = getProductsUseCase.getProducts()
            productsLiveData.postValue(products)
        }
    }
}