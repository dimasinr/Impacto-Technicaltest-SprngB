package com.example.impacto.controller

import com.example.impacto.dto.ProductDTO
import com.example.impacto.dto.ProductResponse
import com.example.impacto.dto.ResponseMessage
import com.example.impacto.service.ProductService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/product")
@Tag(name = "Product")
class ProductController(
    private val productService: ProductService
) {

    @Operation(summary = "List of Products")
    @GetMapping
    fun getAllProducts(): List<ProductResponse> {
        return productService.getAllProducts()
    }

    @Operation(summary = "Add new Product")
    @PostMapping
    fun createProduct(@Valid @RequestBody dto: ProductDTO): ResponseMessage {
        productService.createProduct(dto)
        return ResponseMessage("Success")
    }

    @Operation(summary = "Get Product by id")
    @GetMapping("/{id}")
    fun getProductById(@PathVariable id: Long): ProductResponse {
        return productService.getProductById(id)
    }

    @Operation(summary = "Update Product")
    @PutMapping("/{id}")
    fun updateProduct(@PathVariable id: Long, @Valid @RequestBody dto: ProductDTO): ResponseMessage {
        productService.updateProduct(id, dto)
        return ResponseMessage("Success")
    }

    @Operation(summary = "Delete Product")
    @DeleteMapping("/{id}")
    fun deleteProduct(@PathVariable id: Long): ResponseMessage {
        productService.deleteProduct(id)
        return ResponseMessage("Success")
    }
}
