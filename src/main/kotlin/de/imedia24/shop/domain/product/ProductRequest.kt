package de.imedia24.shop.domain.product

import de.imedia24.shop.db.entity.ProductEntity
import de.imedia24.shop.domain.product.StockResponse.Companion.toStockResponse
import java.math.BigDecimal

data class ProductRequest(
        val sku: String,
        val name: String,
        val description: String,
        val price: BigDecimal,
        val stock: List<StockResponse>,
) {
    companion object {
        fun ProductRequest.toProductEntity() = ProductEntity(
                sku=sku,name=name,
                description=description,
                price=price,
                createdAt = null,
                updatedAt =null,
                )
    }
}