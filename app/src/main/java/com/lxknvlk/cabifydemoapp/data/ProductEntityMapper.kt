package com.lxknvlk.cabifydemoapp.data

import com.lxknvlk.cabifydemoapp.data.api.ProductDTO
import com.lxknvlk.cabifydemoapp.domain.entity.*

class ProductEntityMapper {
    fun mapDTOToEntities(dtoList: List<ProductDTO>): List<Product>{
        val productList: List<Product> = dtoList.map { p ->
            when (p.code){
                ProductCode.VOUCHER -> ProductVoucher(p.code, p.name, p.price)
                ProductCode.TSHIRT -> ProductTShirt(p.code, p.name, p.price)
                ProductCode.MUG -> ProductMug(p.code, p.name, p.price)
            }
        }

        return productList
    }
}