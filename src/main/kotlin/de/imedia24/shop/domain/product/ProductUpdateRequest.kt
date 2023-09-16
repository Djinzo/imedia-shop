package de.imedia24.shop.domain.product

import de.imedia24.shop.db.entity.ProductEntity
import de.imedia24.shop.domain.product.StockResponse.Companion.toStockResponse
import java.math.BigDecimal

data class ProductUpdateRequest(
        val name: String,
        val description: String,
        val price: BigDecimal,
)