package com.lxknvlk.cabifydemoapp.data.di

import com.lxknvlk.cabifydemoapp.data.api.ApiClient
import com.lxknvlk.cabifydemoapp.data.api.ApiInterface
import com.lxknvlk.cabifydemoapp.data.api.RetrofitClient
import com.lxknvlk.cabifydemoapp.domain.GetProductsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@InstallIn(SingletonComponent::class)
@Module
class DiModule {

    @Provides
    fun provideRetrofitClient(): RetrofitClient {
        return RetrofitClient()
    }

    @Provides
    fun provideApiInterface(retrofitClient: RetrofitClient): ApiInterface {
        return retrofitClient.getInstance().create(ApiInterface::class.java)
    }

    @Provides
    fun provideApiClient(apiInterface: ApiInterface): ApiClient {
        return ApiClient(apiInterface)
    }

    @Provides
    fun provideGetProductsUseCase(
        apiClient: ApiClient
    ): GetProductsUseCase {
        return GetProductsUseCase(apiClient)
    }

    @Provides
    fun provideCoroutineDispatcherIO(): CoroutineDispatcher {
        return Dispatchers.IO
    }
}