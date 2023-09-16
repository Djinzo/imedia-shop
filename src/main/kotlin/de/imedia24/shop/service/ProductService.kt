package de.imedia24.shop.service

import de.imedia24.shop.db.entity.ProductEntity
import de.imedia24.shop.db.entity.StockEntity
import de.imedia24.shop.db.repository.ProductRepository
import de.imedia24.shop.db.repository.StockRepository
import de.imedia24.shop.domain.product.ProductRequest
import de.imedia24.shop.domain.product.ProductResponse
import de.imedia24.shop.domain.product.ProductResponse.Companion.toProductResponse
import de.imedia24.shop.domain.product.ProductUpdateRequest
import org.springframework.stereotype.Service

@Service
class ProductService(private val productRepository: ProductRepository, private val stockRepository: StockRepository) {

    fun findProductBySku(sku: String): ProductResponse? {
        val productEntity = productRepository.findBySku(sku);
        return productEntity?.toProductResponse();
    }

    fun findProductsBySkus(skus: Array<String>): List<ProductResponse>? {
        val productEntitys = productRepository.findBySkuIn(skus);
        return productEntitys?.map { p -> p.toProductResponse() }
    }

    fun addProduct(productRequest: ProductRequest): ProductResponse? {
        val existingProduct = productRepository.findBySku(productRequest.sku)
        if (existingProduct != null) {
            return null
        }
        val product = ProductEntity(
                productRequest.sku,
                productRequest.name,
                productRequest.description,
                productRequest.price,
                null, null
        )

        productRepository.save(product)

        val stock = productRequest.stock.map { s -> StockEntity(s.id, s.quantity, s.warehouse, product) }

        stockRepository.saveAll(stock)

        product.stockList = stock

        return product.toProductResponse()
    }

    fun updateProduct(sku: String, productRequest: ProductUpdateRequest): ProductResponse? {
        val existingProduct = productRepository.findBySku(sku)

        if (existingProduct != null) {
            val updatedProduct = existingProduct.copy(
                    name = productRequest.name,
                    description = productRequest.description,
                    price = productRequest.price
            )

            val savedProduct = productRepository.save(updatedProduct)
            return savedProduct.toProductResponse()
        }

        return null
    }

}
