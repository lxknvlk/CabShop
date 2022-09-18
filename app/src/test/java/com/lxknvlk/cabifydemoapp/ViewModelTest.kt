package com.lxknvlk.cabifydemoapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.lxknvlk.cabifydemoapp.domain.entity.Product
import com.lxknvlk.cabifydemoapp.domain.entity.ProductCode
import com.lxknvlk.cabifydemoapp.domain.usecases.CheckoutUseCase
import com.lxknvlk.cabifydemoapp.domain.usecases.GetProductsUseCase
import com.lxknvlk.cabifydemoapp.presentation.MainViewModel
import junit.framework.Assert
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(JUnit4::class)
class ViewModelTest {

    @Mock
    private lateinit var checkoutUseCase: CheckoutUseCase

    @Mock
    private lateinit var getProductsUseCase: GetProductsUseCase

    private lateinit var mainViewModel: MainViewModel

    private val dispatcher = StandardTestDispatcher()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val PRODUCT_VOUCHER = Product(ProductCode.VOUCHER, "VOUCHER", 5.00)
    private val PRODUCT_TSHIRT = Product(ProductCode.TSHIRT, "TSHIRT", 20.00)
    private val PRODUCT_MUG = Product(ProductCode.MUG, "VOUCHER", 7.50)


    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(dispatcher)

        mainViewModel = MainViewModel(getProductsUseCase, dispatcher, checkoutUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun testMainViewModelProductsLiveData() = runTest(UnconfinedTestDispatcher()) {
        val productList = mutableListOf<Product>()
        productList.add(PRODUCT_VOUCHER)
        productList.add(PRODUCT_TSHIRT)
        productList.add(PRODUCT_MUG)

        Mockito.`when`(getProductsUseCase.getProducts()).thenReturn(productList)

        mainViewModel.getProducts()
        advanceUntilIdle()

        Assert.assertEquals(mainViewModel.productsLiveData.value, productList)
    }
}