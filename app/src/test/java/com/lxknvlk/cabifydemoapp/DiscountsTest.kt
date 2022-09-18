package com.lxknvlk.cabifydemoapp

import com.lxknvlk.cabifydemoapp.domain.discounts.DiscountCalculator
import com.lxknvlk.cabifydemoapp.domain.discounts.MugDiscount
import com.lxknvlk.cabifydemoapp.domain.discounts.TShirtDiscount
import com.lxknvlk.cabifydemoapp.domain.discounts.VoucherDiscount
import com.lxknvlk.cabifydemoapp.domain.entity.Product
import com.lxknvlk.cabifydemoapp.domain.entity.ProductCode
import com.lxknvlk.cabifydemoapp.domain.entity.ShoppingCart
import com.lxknvlk.cabifydemoapp.domain.usecases.CheckoutUseCase
import com.lxknvlk.cabifydemoapp.domain.utils.ReceiptCreator
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class DiscountsTest {

    private lateinit var voucherDiscount: VoucherDiscount
    private lateinit var mugDiscount: MugDiscount
    private lateinit var tShirtDiscount: TShirtDiscount
    private lateinit var discountCalculator: DiscountCalculator
    private lateinit var checkoutUseCase: CheckoutUseCase
    private lateinit var receiptCreator: ReceiptCreator

    private val PRODUCT_VOUCHER = Product(ProductCode.VOUCHER, "VOUCHER", 5.00)
    private val PRODUCT_TSHIRT = Product(ProductCode.TSHIRT, "TSHIRT", 20.00)
    private val PRODUCT_MUG = Product(ProductCode.MUG, "VOUCHER", 7.50)

    @Before
    fun setup(){
        voucherDiscount = VoucherDiscount()
        mugDiscount = MugDiscount()
        tShirtDiscount = TShirtDiscount()

        discountCalculator = DiscountCalculator(voucherDiscount, tShirtDiscount, mugDiscount)
        receiptCreator = ReceiptCreator()
        checkoutUseCase = CheckoutUseCase(discountCalculator, receiptCreator)
    }

    @Test
    fun checkoutTestOne() {
        val productList = mutableListOf<Product>()
        productList.add(PRODUCT_VOUCHER)
        productList.add(PRODUCT_TSHIRT)
        productList.add(PRODUCT_MUG)

        val shoppingCart = ShoppingCart()
        shoppingCart.addProducts(productList)

        val receipt = checkoutUseCase.checkout(shoppingCart)

        assertEquals(receipt.totalPrice, 32.50, 0.0)
    }

    @Test
    fun checkoutTestTwo() {
        val productList = mutableListOf<Product>()
        productList.add(PRODUCT_VOUCHER)
        productList.add(PRODUCT_TSHIRT)
        productList.add(PRODUCT_VOUCHER)

        val shoppingCart = ShoppingCart()
        shoppingCart.addProducts(productList)

        val receipt = checkoutUseCase.checkout(shoppingCart)

        assertEquals(receipt.totalPrice, 25.00, 0.0)
    }

    @Test
    fun checkoutTestThree() {
        val productList = mutableListOf<Product>()
        productList.add(PRODUCT_TSHIRT)
        productList.add(PRODUCT_TSHIRT)
        productList.add(PRODUCT_TSHIRT)
        productList.add(PRODUCT_VOUCHER)
        productList.add(PRODUCT_TSHIRT)

        val shoppingCart = ShoppingCart()
        shoppingCart.addProducts(productList)

        val receipt = checkoutUseCase.checkout(shoppingCart)

        assertEquals(receipt.totalPrice, 81.00, 0.0)
    }

    @Test
    fun checkoutTestFour() {
        val productList = mutableListOf<Product>()
        productList.add(PRODUCT_VOUCHER)
        productList.add(PRODUCT_TSHIRT)
        productList.add(PRODUCT_VOUCHER)
        productList.add(PRODUCT_VOUCHER)
        productList.add(PRODUCT_MUG)
        productList.add(PRODUCT_TSHIRT)
        productList.add(PRODUCT_TSHIRT)

        val shoppingCart = ShoppingCart()
        shoppingCart.addProducts(productList)

        val receipt = checkoutUseCase.checkout(shoppingCart)

        assertEquals(receipt.totalPrice, 74.50, 0.0)
    }
}