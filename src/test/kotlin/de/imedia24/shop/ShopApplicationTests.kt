package de.imedia24.shop

import com.fasterxml.jackson.databind.ObjectMapper
import de.imedia24.shop.controller.ProductController
import de.imedia24.shop.db.entity.ProductEntity
import de.imedia24.shop.domain.product.ProductResponse
import de.imedia24.shop.domain.product.ProductUpdateRequest
import de.imedia24.shop.domain.product.StockResponse
import de.imedia24.shop.service.ProductService
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.math.BigDecimal

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ShopApplicationTests(
        @Autowired private val mockMvc: MockMvc,
        @Autowired private val objectMapper: ObjectMapper
) {


    @MockBean
    private lateinit var productService: ProductService

    lateinit var products: List<ProductResponse>


    @BeforeAll
    fun setUp() {
        val skus = arrayOf("SKU001", "SKU002", "SKU003")
        products = skus.map { s -> ProductResponse(s, "name" + s, "discription" + s, BigDecimal.TEN, emptyList()) }
    }

    @Test
    fun find_products_by_skus() {
        val skus = listOf("SKU001", "SKU002", "SKU003")
        `when`(productService.findProductsBySkus(skus)).thenReturn(products)
        mockMvc.perform(
                MockMvcRequestBuilders.get("/products?skus={skus}", skus)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].sku").value(products.get(0).sku))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(products.get(0).name))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].description").value(products.get(0).description))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].price").value(products.get(0).price))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].sku").value(products.get(1).sku))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value(products.get(1).name))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].description").value(products.get(1).description))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].price").value(products.get(1).price))
    }
    @Test
    fun find_products_by_skus_notExist() {
        val skus = listOf("SKU004", "SKU005", "SKU007")
        `when`(productService.findProductsBySkus(skus)).thenReturn(emptyList())
        mockMvc.perform(
                MockMvcRequestBuilders.get("/products?skus={skus}", skus)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotFound)
    }
    @Test
    fun update_product_by_sku() {
        val sku = "SKU001";
        val productUpdateRequest = ProductUpdateRequest("updated name","updated descreption", BigDecimal.TEN);
        val productResponse = ProductResponse(sku,
                productUpdateRequest.name?:"",
                productUpdateRequest.description?:"",
                productUpdateRequest.price?:BigDecimal.ZERO,
                emptyList())

        `when`(productService.updateProduct(sku,productUpdateRequest)).thenReturn(productResponse)

        mockMvc.perform(
                MockMvcRequestBuilders.patch("/product/{sku}", sku)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productUpdateRequest))
        ).andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.sku").value(productResponse.sku))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(productResponse.name))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(productResponse.description))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(productResponse.price))
    }

    @Test
    fun add_product(){

    }


}
