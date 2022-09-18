package com.lxknvlk.cabifydemoapp.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lxknvlk.cabifydemoapp.data.api.ProductResponse
import com.lxknvlk.cabifydemoapp.domain.CheckoutUseCase
import com.lxknvlk.cabifydemoapp.domain.GetProductsUseCase
import com.lxknvlk.cabifydemoapp.domain.ProductEntity
import com.lxknvlk.cabifydemoapp.domain.ShoppingCart
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

    val productsLiveData = MutableLiveData<List<ProductEntity>?>()
    val receiptLiveData = MutableLiveData<String>()

    private val shoppingCart = ShoppingCart()

    fun getProducts(){
        viewModelScope.launch(coroutineDispatcherIO) {
            val products: List<ProductEntity>? = getProductsUseCase.getProducts()

            //simulating slower server request to show nice loading indicator
            Thread.sleep(2000)

            productsLiveData.postValue(products)
        }
    }

    fun addProduct(product: ProductEntity){
        shoppingCart.addProduct(product)
    }

    fun removeProduct(product: ProductEntity){
        shoppingCart.removeProduct(product)
    }

    fun checkout(){
        val receipt = checkoutUseCase.checkout(shoppingCart)

        receiptLiveData.postValue(receipt)
    }
}