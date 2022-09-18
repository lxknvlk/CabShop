package com.lxknvlk.cabifydemoapp.data.di

import com.lxknvlk.cabifydemoapp.data.api.ApiClient
import com.lxknvlk.cabifydemoapp.data.api.ApiInterface
import com.lxknvlk.cabifydemoapp.data.api.RetrofitClient
import com.lxknvlk.cabifydemoapp.domain.usecases.CheckoutUseCase
import com.lxknvlk.cabifydemoapp.domain.usecases.GetProductsUseCase
import com.lxknvlk.cabifydemoapp.domain.utils.ReceiptCreator
import com.lxknvlk.cabifydemoapp.domain.discounts.DiscountCalculator
import com.lxknvlk.cabifydemoapp.domain.discounts.MugDiscount
import com.lxknvlk.cabifydemoapp.domain.discounts.TShirtDiscount
import com.lxknvlk.cabifydemoapp.domain.discounts.VoucherDiscount
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

    @Provides
    fun provideVoucherDiscount(): VoucherDiscount{
        return VoucherDiscount()
    }

    @Provides
    fun provideTShirtDiscount(): TShirtDiscount{
        return TShirtDiscount()
    }

    @Provides
    fun provideMugDiscount(): MugDiscount{
        return MugDiscount()
    }

    @Provides
    fun provideReceiptCreator(): ReceiptCreator {
        return ReceiptCreator()
    }

    @Provides
    fun provideCheckoutUseCase(
        discountCalculator: DiscountCalculator,
        receiptCreator: ReceiptCreator
    ): CheckoutUseCase {
        return CheckoutUseCase(discountCalculator, receiptCreator)
    }

    @Provides
    fun provideDiscountCalculator(
        voucherDiscount: VoucherDiscount,
        tShirtDiscount: TShirtDiscount,
        mugDiscount: MugDiscount
    ): DiscountCalculator{
        return DiscountCalculator(voucherDiscount, tShirtDiscount, mugDiscount)
    }
}