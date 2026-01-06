package com.project.track.cash.model.mapper

import com.project.track.cash.domain.Product
import com.project.track.cash.model.ProductResponse
import com.project.track.cash.resources.request.ProductRequest

object ProductMapper {

    fun toEntity(request: ProductRequest): Product = Product(
        name = request.name,
        description = request.description,
        price = request.price,
        active = request.active
    )

    fun toResponse(product: Product): ProductResponse = ProductResponse(
        id = product.id,
        name = product.name,
        description = product.description,
        price = product.price,
        active = product.active,
        createdAt = product.createdAt,
        updatedAt = product.updatedAt
    )

    fun toResponseList(products: List<Product>): List<ProductResponse> =
        products.map { toResponse(it) }
}