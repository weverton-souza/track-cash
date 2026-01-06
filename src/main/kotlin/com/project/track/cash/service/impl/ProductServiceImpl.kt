package com.project.track.cash.service.impl

import com.project.track.cash.domain.Product
import com.project.track.cash.model.ProductResponse
import com.project.track.cash.model.mapper.ProductMapper
import com.project.track.cash.repository.ProductRepository
import com.project.track.cash.resources.request.ProductRequest
import com.project.track.cash.service.ProductService
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

@Service
@Transactional
class ProductServiceImpl(
    private val productRepository: ProductRepository
) : ProductService {

    @Transactional(readOnly = true)
    override fun findById(id: UUID): ProductResponse {
        val product = findProductById(id)
        return ProductMapper.toResponse(product)
    }

    @Transactional(readOnly = true)
    override fun findAll(pageable: Pageable): Page<ProductResponse> {
        return productRepository.findAll(pageable)
            .map { ProductMapper.toResponse(it) }
    }

    override fun create(request: ProductRequest): ProductResponse {
        val product = ProductMapper.toEntity(request)
        val savedProduct = productRepository.save(product)
        return ProductMapper.toResponse(savedProduct)
    }

    override fun update(id: UUID, request: ProductRequest): ProductResponse {
        val product = findProductById(id)

        product.apply {
            name = request.name
            description = request.description
            price = request.price
            active = request.active
        }

        val updatedProduct = productRepository.save(product)
        return ProductMapper.toResponse(updatedProduct)
    }

    override fun delete(id: UUID) {
        val product = findProductById(id)
        productRepository.delete(product)
    }

    override fun activate(id: UUID): ProductResponse {
        val product = findProductById(id)
        product.active = true
        val savedProduct = productRepository.save(product)
        return ProductMapper.toResponse(savedProduct)
    }

    override fun deactivate(id: UUID): ProductResponse {
        val product = findProductById(id)
        product.active = false
        val savedProduct = productRepository.save(product)
        return ProductMapper.toResponse(savedProduct)
    }

    private fun findProductById(id: UUID): Product {
        return productRepository.findById(id)
            .orElseThrow { EntityNotFoundException("Produto não encontrado com id: $id") }
    }
}