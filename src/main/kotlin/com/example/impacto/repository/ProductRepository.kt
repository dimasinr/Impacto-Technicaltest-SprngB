package com.example.impacto.repository

import com.example.impacto.entity.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<Product, Long> {
    fun existsByCode(code: String): Boolean
    
    @Query("SELECT p.code FROM Product p WHERE p.code LIKE :prefix% ORDER BY p.code DESC LIMIT 1")
    fun findLatestCodeByPrefix(prefix: String): String?
}
