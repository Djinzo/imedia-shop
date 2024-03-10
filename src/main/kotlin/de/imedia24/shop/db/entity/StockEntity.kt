package de.imedia24.shop.db.entity

import de.imedia24.shop.domain.product.ProductRequest
import de.imedia24.shop.domain.product.StockResponse
import javax.persistence.*


@Entity
@Table(name = "stock")
data class StockEntity(
        @Id
        //@GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        @Column(name = "quantity", nullable = false)
        val quantity: Int,

        @Column(name = "warehouse", nullable = false)
        val warehouse: String,
        @ManyToOne
        @JoinColumn(name = "sku", referencedColumnName = "sku")
        val product: ProductEntity

) {
    constructor() : this(0, 100, "Warehouse 1 ", ProductEntity()) {

    }

}
