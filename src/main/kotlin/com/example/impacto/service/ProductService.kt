package com.example.impacto.service

import com.example.impacto.dto.*
import com.example.impacto.entity.Product
import com.example.impacto.repository.CategoryRepository
import com.example.impacto.repository.ProductRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val categoryRepository: CategoryRepository
) {

    @Transactional(readOnly = true)
    fun getAllProducts(): List<ProductResponse> {
        return productRepository.findAll().map { p ->
            ProductResponse(
                id = p.id,
                code = p.code,
                name = p.name,
                price = p.price,
                categoryId = p.category?.id,
                categoryDesc = p.category?.name
            )
        }
    }

    @Transactional(readOnly = true)
    fun getProductById(id: Long): ProductResponse {
        val p = productRepository.findByIdOrNull(id) ?: throw RuntimeException("Product not found")
        return ProductResponse(
            id = p.id,
            code = p.code,
            name = p.name,
            price = p.price,
            categoryId = p.category?.id,
            categoryDesc = p.category?.name
        )
    }

    @Transactional
    fun createProduct(dto: ProductDTO) {
        val finalCode = generateUniqueCode(dto.code)

        val category = dto.categoryId?.let {
            categoryRepository.findByIdOrNull(it) ?: throw RuntimeException("Category not found")
        }

        val product = Product(
            code = finalCode,
            name = dto.name,
            price = dto.price,
            category = category
        )
        productRepository.save(product)
    }

    @Transactional
    fun updateProduct(id: Long, dto: ProductDTO) {
        val p = productRepository.findByIdOrNull(id) ?: throw RuntimeException("Product not found")

        val category = dto.categoryId?.let {
            categoryRepository.findByIdOrNull(it) ?: throw RuntimeException("Category not found")
        }

        var newCode = dto.code
        if (newCode != null && newCode != p.code) {
           newCode = generateUniqueCode(newCode)
           p.code = newCode
        }

        p.name = dto.name
        p.price = dto.price
        p.category = category

        productRepository.save(p)
    }

    @Transactional
    fun deleteProduct(id: Long) {
        if (!productRepository.existsById(id)) {
            throw RuntimeException("Product not found")
        }
        productRepository.deleteById(id)
    }

    private fun generateUniqueCode(inputCode: String?): String {
        var currentCode = if (inputCode.isNullOrBlank()) {
            val latest = productRepository.findLatestCodeByPrefix("PRD-")
            if (latest != null) {
                val num = latest.substringAfterLast("-").toIntOrNull() ?: 0
                "PRD-%03d".format(num + 1)
            } else {
                "PRD-001"
            }
        } else {
            inputCode
        }

        var i = 1
        val originalBaseCode = currentCode
        while (productRepository.existsByCode(currentCode)) {
            // Increase 1 in the last of code
            currentCode = "$originalBaseCode-$i"
            i++
        }
        return currentCode
    }
}
