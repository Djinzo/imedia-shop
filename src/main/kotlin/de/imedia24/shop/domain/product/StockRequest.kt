package de.imedia24.shop.domain.product

import de.imedia24.shop.db.entity.ProductEntity
import de.imedia24.shop.db.entity.StockEntity
import javax.persistence.Column
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

data class StockRequest(
        val id: Long,
        val quantity: Int,
        val warehouse: String)