package com.example.impacto.service

import com.example.impacto.dto.*
import com.example.impacto.entity.Category
import com.example.impacto.repository.CategoryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CategoryService(
    private val categoryRepository: CategoryRepository
) {
    @Transactional(readOnly = true)
    fun getAllCategories(): List<CategoryDTO> {
        return categoryRepository.findAll().map { category ->
            CategoryDTO(
                id = category.id,
                name = category.name,
                products = category.products.map { p ->
                    CategoryProductResponse(
                        id = p.id,
                        code = p.code,
                        name = p.name,
                        price = p.price,
                        categoryId = category.id
                    )
                }
            )
        }
    }

    @Transactional
    fun createCategory(request: CategoryRequest) {
        val category = Category(name = request.name)
        categoryRepository.save(category)
    }
}
