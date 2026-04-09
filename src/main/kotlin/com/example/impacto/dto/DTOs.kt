package com.example.impacto.dto

import java.math.BigDecimal
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class ResponseMessage(val message: String)

data class ProductDTO(
    var code: String? = null,
    @field:NotBlank
    val name: String,
    @field:NotNull
    val price: BigDecimal,
    val categoryId: Long?
)

data class ProductResponse(
    val id: Long,
    val code: String,
    val name: String,
    val price: BigDecimal,
    val categoryId: Long?,
    val categoryDesc: String?
)

data class CategoryRequest(
    @field:NotBlank
    val name: String
)

data class CategoryDTO(
    val id: Long,
    val name: String,
    val products: List<CategoryProductResponse>
)

data class CategoryProductResponse(
    val id: Long,
    val code: String,
    val name: String,
    val price: BigDecimal,
    val categoryId: Long?
)
