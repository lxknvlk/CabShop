package com.lxknvlk.cabifydemoapp

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.lxknvlk.cabifydemoapp.data.api.ApiClient
import com.lxknvlk.cabifydemoapp.data.api.ApiInterface
import com.lxknvlk.cabifydemoapp.data.repository.ProductRepositoryRemoteImpl
import com.lxknvlk.cabifydemoapp.domain.interfaces.ProductRepositoryRemote
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(JUnit4::class)
class RemoteRepositoryTest {

    @Mock
    lateinit var mockWebServer: MockWebServer

    @Mock
    lateinit var apiInterface: ApiInterface
    lateinit var gson: Gson

    private lateinit var apiClient: ApiClient
    private lateinit var productRepositoryRemote: ProductRepositoryRemote

    @Before
    fun setup() {
        gson = GsonBuilder().create()
        mockWebServer = MockWebServer()

        apiInterface = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiInterface::class.java)


        apiClient = ApiClient(apiInterface)
        productRepositoryRemote = ProductRepositoryRemoteImpl(apiClient)
    }

    @After
    fun deconstruct() {
        mockWebServer.shutdown()
    }

    @Test
    fun testGetProductsEndpoint() = runTest {
        val mockResponse = MockResponse()

        val mockResp = """
            {
              "products": [
                {
                  "code": "VOUCHER",
                  "name": "Cabify Voucher",
                  "price": 5
                },
                {
                  "code": "TSHIRT",
                  "name": "Cabify T-Shirt",
                  "price": 20
                },
                {
                  "code": "MUG",
                  "name": "Cabify Coffee Mug",
                  "price": 7.5
                }
              ]
            }
            """.trimIndent()

        mockWebServer.enqueue(
            mockResponse.setBody(mockResp)
        )

        val productList = productRepositoryRemote.getProducts()

        assert(productList!!.isNotEmpty())
    }
}