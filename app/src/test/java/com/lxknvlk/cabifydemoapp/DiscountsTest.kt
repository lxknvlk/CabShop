package com.lxknvlk.cabifydemoapp

import com.lxknvlk.cabifydemoapp.domain.discounts.DiscountCalculator
import com.lxknvlk.cabifydemoapp.domain.discounts.MugDiscount
import com.lxknvlk.cabifydemoapp.domain.discounts.TShirtDiscount
import com.lxknvlk.cabifydemoapp.domain.discounts.VoucherDiscount
import com.lxknvlk.cabifydemoapp.domain.entity.*
import com.lxknvlk.cabifydemoapp.domain.usecases.CheckoutUseCase
import com.lxknvlk.cabifydemoapp.domain.utils.ReceiptCreator
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

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

    private val PRODUCT_VOUCHER = ProductVoucher(ProductCode.VOUCHER, "VOUCHER", 5.00)
    private val PRODUCT_TSHIRT = ProductTShirt(ProductCode.TSHIRT, "TSHIRT", 20.00)
    private val PRODUCT_MUG = ProductMug(ProductCode.MUG, "VOUCHER", 7.50)

    @Before
    fun setup() {
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

    @Test
    fun testVoucherDiscount() {
        val resultProductListOne = voucherDiscount.applyDiscount(listOf(PRODUCT_VOUCHER))
        assertEquals(resultProductListOne[0].price, PRODUCT_VOUCHER.price, 0.0)

        val resultProductListTwo = voucherDiscount.applyDiscount(listOf(PRODUCT_VOUCHER, PRODUCT_VOUCHER))
        assertEquals(resultProductListTwo[0].price, PRODUCT_VOUCHER.price, 0.0)
        assertEquals(resultProductListTwo[1].price, 0.0, 0.0)

        val resultProductListThree = voucherDiscount.applyDiscount(listOf(PRODUCT_VOUCHER, PRODUCT_VOUCHER, PRODUCT_VOUCHER))
        assertEquals(resultProductListThree[0].price, PRODUCT_VOUCHER.price, 0.0)
        assertEquals(resultProductListThree[1].price, 0.0, 0.0)
        assertEquals(resultProductListThree[2].price, PRODUCT_VOUCHER.price, 0.0)

        val resultProductListFour = voucherDiscount.applyDiscount(listOf(PRODUCT_VOUCHER, PRODUCT_VOUCHER, PRODUCT_VOUCHER, PRODUCT_VOUCHER))
        assertEquals(resultProductListFour[0].price, PRODUCT_VOUCHER.price, 0.0)
        assertEquals(resultProductListFour[1].price, 0.0, 0.0)
        assertEquals(resultProductListFour[2].price, PRODUCT_VOUCHER.price, 0.0)
        assertEquals(resultProductListFour[3].price, 0.0, 0.0)
    }

    @Test
    fun testTShirtDiscount(){
        val resultProductListOne = tShirtDiscount.applyDiscount(listOf(PRODUCT_TSHIRT))
        assertEquals(resultProductListOne[0].price, PRODUCT_TSHIRT.price, 0.0)

        val resultProductListTwo = tShirtDiscount.applyDiscount(listOf(PRODUCT_TSHIRT, PRODUCT_TSHIRT))
        assertEquals(resultProductListTwo[0].price, PRODUCT_TSHIRT.price, 0.0)
        assertEquals(resultProductListTwo[1].price, PRODUCT_TSHIRT.price, 0.0)

        val resultProductListThree = tShirtDiscount.applyDiscount(listOf(PRODUCT_TSHIRT, PRODUCT_TSHIRT, PRODUCT_TSHIRT))
        assertEquals(resultProductListThree[0].price, 19.00, 0.0)
        assertEquals(resultProductListThree[1].price, 19.00, 0.0)
        assertEquals(resultProductListThree[2].price, 19.00, 0.0)

        val resultProductListFour = tShirtDiscount.applyDiscount(listOf(PRODUCT_TSHIRT, PRODUCT_TSHIRT, PRODUCT_TSHIRT, PRODUCT_TSHIRT))
        assertEquals(resultProductListFour[0].price, 19.00, 0.0)
        assertEquals(resultProductListFour[1].price, 19.00, 0.0)
        assertEquals(resultProductListFour[2].price, 19.00, 0.0)
        assertEquals(resultProductListFour[3].price, 19.00, 0.0)
    }

    @Test
    fun testMugDiscount(){
        val resultProductListOne = mugDiscount.applyDiscount(listOf(PRODUCT_MUG))
        assertEquals(resultProductListOne[0].price, PRODUCT_MUG.price, 0.0)

        val resultProductListTwo = mugDiscount.applyDiscount(listOf(PRODUCT_MUG, PRODUCT_MUG, PRODUCT_MUG, PRODUCT_MUG, PRODUCT_MUG))
        assertEquals(resultProductListTwo[0].price, PRODUCT_MUG.price, 0.0)
        assertEquals(resultProductListTwo[1].price, PRODUCT_MUG.price, 0.0)
        assertEquals(resultProductListTwo[2].price, PRODUCT_MUG.price, 0.0)
        assertEquals(resultProductListTwo[3].price, PRODUCT_MUG.price, 0.0)
        assertEquals(resultProductListTwo[4].price, PRODUCT_MUG.price, 0.0)
    }
}