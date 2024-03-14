package de.imedia24.shop.service

import de.imedia24.shop.db.entity.ProductEntity
import de.imedia24.shop.db.entity.StockEntity
import de.imedia24.shop.db.repository.ProductRepository
import de.imedia24.shop.db.repository.StockRepository
import de.imedia24.shop.domain.product.ProductRequest
import de.imedia24.shop.domain.product.ProductRequest.Companion.toProductEntity
import de.imedia24.shop.domain.product.ProductResponse
import de.imedia24.shop.domain.product.ProductResponse.Companion.toProductResponse
import de.imedia24.shop.domain.product.ProductUpdateRequest
import org.springframework.stereotype.Service

@Service
class ProductService(private val productRepository: ProductRepository, private val stockRepository: StockRepository) {

    fun findProductBySku(sku: String): ProductResponse? {
        val productEntity = productRepository.findBySku(sku)
                .orElse(null)
        return productEntity?.toProductResponse();
    }

    fun findProductsBySkus(skus: List<String>): List<ProductResponse>? {
        val productEntities = productRepository.findBySkuIn(skus);
        return productEntities?.map { p -> p.toProductResponse() }
    }

    fun addProduct(productRequest: ProductRequest): ProductResponse? {
        val existingProduct = productRepository.findBySku(productRequest.sku)
        if (existingProduct.isPresent) {
            throw IllegalArgumentException("sku already exist")
        }

        val product =productRequest.toProductEntity()
        product.stockList = productRequest.stock.map { s -> StockEntity(s.id,s.quantity,s.warehouse,product) }

        return productRepository.save(product).toProductResponse()
    }

    fun updateProduct(sku: String, productRequest: ProductUpdateRequest): ProductResponse? {
        var product = productRepository.findBySku(sku)
                .orElseThrow{IllegalStateException("product not found")}

        product = product.copy(name = productRequest.name?:product.name,
                description = productRequest.description?:product.description,
                price = productRequest.price?:product.price)

        return productRepository.save(product).toProductResponse();

    }

}
