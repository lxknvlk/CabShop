package com.lxknvlk.cabifydemoapp.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lxknvlk.cabifydemoapp.domain.usecase.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val coroutineDispatcherIO: CoroutineDispatcher
) : ViewModel() {
    val productsLiveData = MutableLiveData<Any>()

    fun getProducts(){
        viewModelScope.launch(coroutineDispatcherIO) {
            val products = getProductsUseCase.getProducts()

            productsLiveData.postValue(products)
        }
    }
}