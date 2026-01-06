package com.project.track.cash.service

import com.project.track.cash.model.ProductResponse
import com.project.track.cash.resources.request.ProductRequest
import java.util.UUID
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ProductService {

    fun create(request: ProductRequest): ProductResponse

    fun update(id: UUID, request: ProductRequest): ProductResponse

    fun findById(id: UUID): ProductResponse

    fun findAll(pageable: Pageable): Page<ProductResponse>

    fun delete(id: UUID)

    fun activate(id: UUID): ProductResponse

    fun deactivate(id: UUID): ProductResponse
}
