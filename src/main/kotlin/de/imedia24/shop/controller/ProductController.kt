package de.imedia24.shop.controller

import de.imedia24.shop.domain.product.ProductRequest
import de.imedia24.shop.domain.product.ProductResponse
import de.imedia24.shop.domain.product.ProductUpdateRequest
import de.imedia24.shop.service.ProductService
import io.swagger.annotations.ApiOperation
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.Arrays
import javax.websocket.server.PathParam

@RestController
class ProductController(private val productService: ProductService) {

    private val logger = LoggerFactory.getLogger(ProductController::class.java)!!


    @ApiOperation("get product by sku")
    @GetMapping("/product/{sku}", produces = ["application/json;charset=utf-8"])
    fun findProductBySku(
            @PathVariable("sku") sku: String
    ): ResponseEntity<ProductResponse> {
        logger.info("Request for product $sku")

        val product = productService.findProductBySku(sku)
        return if (product == null) {
            ResponseEntity.notFound().build()
        } else {
            ResponseEntity.ok(product)
        }
    }


    @ApiOperation("get list of product by skus")
    @GetMapping("/products", produces = ["application/json;charset=utf-8"])
    fun findProductsBySku(@PathParam("skus") skus: List<String>): ResponseEntity<List<ProductResponse>> {
        val products = productService.findProductsBySkus(skus);
        return if (products.isNullOrEmpty()) {
            ResponseEntity.notFound().build()
        } else {
            ResponseEntity.ok(products)
        }
    }


    @ApiOperation("add a new product")
    @PostMapping("/product")
    fun addProduct(@RequestBody productRequest: ProductRequest): ResponseEntity<ProductResponse> {
        var product = productService.addProduct(productRequest)
        return if (product != null) {
            ResponseEntity.status(HttpStatus.CREATED).body(product)
        } else {
            ResponseEntity.status(HttpStatus.CONFLICT).build()
        }
    }


    @ApiOperation("update a prduct by sku")
    @PatchMapping("/product/{sku}")
    fun updateProduct(
            @PathVariable sku: String,
            @RequestBody productUpdateRequest: ProductUpdateRequest
    ): ResponseEntity<ProductResponse> {
        val updatedProduct = productService.updateProduct(sku, productUpdateRequest)

        return if (updatedProduct != null) {
            ResponseEntity.ok(updatedProduct)
        } else {
            ResponseEntity.notFound().build()
        }
    }


}
