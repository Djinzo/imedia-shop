package de.imedia24.shop.domain.product

import de.imedia24.shop.db.entity.ProductEntity
import de.imedia24.shop.db.entity.StockEntity
import javax.persistence.Column
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

data class StockResponse(
        val id: Long,
        val quantity: Int,
        val warehouse: String) {
    companion object {
        fun StockEntity.toStockResponse() = StockResponse(
                id = id, quantity = quantity, warehouse = warehouse
        )
    }
}
