package de.imedia24.shop.db

import de.imedia24.shop.db.entity.ProductEntity
import de.imedia24.shop.db.entity.StockEntity
import de.imedia24.shop.db.repository.ProductRepository
import de.imedia24.shop.db.repository.StockRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.time.ZonedDateTime

@Component
class DataInitializer(
        private val productRepository: ProductRepository,
        private val stockRepository: StockRepository
) : CommandLineRunner {

    override fun run(vararg args: String?) {
        // Add sample data to the database
        val product1 = ProductEntity("SKU001", "Product 1", "Description 1",BigDecimal("15.99"), ZonedDateTime.now(), ZonedDateTime.now())
        val product2 = ProductEntity("SKU002", "Product 2", "Description 2", BigDecimal("15.99"), ZonedDateTime.now(), ZonedDateTime.now())

        productRepository.saveAll(listOf(product1, product2))

        val stock1 = StockEntity(0, 100, "Warehouse 1 ",product1)
        val stock3 = StockEntity(3, 100, "Warehouse 3 ",product1)
        val stock2 = StockEntity(1,100,"Warehouse 2 ",product2)


        stockRepository.saveAll(listOf( stock1,stock2,stock3));

    }
}
