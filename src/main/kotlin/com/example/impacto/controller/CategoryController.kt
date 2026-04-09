package com.example.impacto.controller

import com.example.impacto.dto.CategoryDTO
import com.example.impacto.dto.CategoryRequest
import com.example.impacto.dto.ResponseMessage
import com.example.impacto.service.CategoryService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/category")
@Tag(name = "Category")
class CategoryController(
    private val categoryService: CategoryService
) {

    @Operation(summary = "List of Categories")
    @GetMapping
    fun getAllCategories(): List<CategoryDTO> {
        return categoryService.getAllCategories()
    }

    @Operation(summary = "Add Category")
    @PostMapping
    fun createCategory(@Valid @RequestBody request: CategoryRequest): ResponseMessage {
        categoryService.createCategory(request)
        return ResponseMessage("Success")
    }
}
