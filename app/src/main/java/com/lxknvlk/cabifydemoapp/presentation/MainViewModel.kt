package com.lxknvlk.cabifydemoapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lxknvlk.cabifydemoapp.domain.usecases.CheckoutUseCase
import com.lxknvlk.cabifydemoapp.domain.usecases.GetProductsUseCase
import com.lxknvlk.cabifydemoapp.domain.entity.Product
import com.lxknvlk.cabifydemoapp.domain.entity.ShoppingCart
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val coroutineDispatcherIO: CoroutineDispatcher,
    private val checkoutUseCase: CheckoutUseCase
) : ViewModel() {

    private val productsMutableLiveData = MutableLiveData<List<Product>?>()
    val productsLiveData : LiveData<List<Product>?> by this::productsMutableLiveData

    private val receiptMutableLiveData = MutableLiveData<String>()
    val receiptLiveData : LiveData<String> by this::receiptMutableLiveData


    private val shoppingCart = ShoppingCart()

    fun getProducts(){
        viewModelScope.launch(coroutineDispatcherIO) {
            val products: List<Product>? = getProductsUseCase.getProducts()

            //simulating slower server request to show nice loading indicator
            Thread.sleep(2000)

            productsMutableLiveData.postValue(products)
        }
    }

    fun addProduct(product: Product){
        shoppingCart.addProduct(product)
    }

    fun removeProduct(product: Product){
        shoppingCart.removeProduct(product)
    }

    fun checkout(){
        val receipt = checkoutUseCase.checkout(shoppingCart)
        receiptMutableLiveData.postValue(receipt)
    }
}