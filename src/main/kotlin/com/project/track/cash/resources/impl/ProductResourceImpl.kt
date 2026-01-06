package com.project.track.cash.resources.impl

import com.project.track.cash.model.ProductResponse
import com.project.track.cash.resources.ProductResource
import com.project.track.cash.resources.request.ProductRequest
import com.project.track.cash.service.ProductService
import jakarta.validation.Valid
import java.util.UUID
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Validated
@RestController
@CrossOrigin
@RequestMapping("/products")
class ProductResourceImpl(
    private val productService: ProductService
) : ProductResource {

    @PostMapping
    override fun create(@RequestBody @Valid request: ProductRequest): ResponseEntity<ProductResponse> {
        return responseEntity(productService.create(request))
    }

    @PutMapping("/{id}")
    override fun update(
        @PathVariable id: UUID,
        @RequestBody @Valid request: ProductRequest
    ): ResponseEntity<ProductResponse> {
        return responseEntity(productService.update(id, request))
    }

    @GetMapping("/{id}")
    override fun findById(@PathVariable id: UUID): ResponseEntity<ProductResponse> {
        return responseEntity(productService.findById(id))
    }

    @GetMapping
    override fun findAll(parameters: Map<String, Any>): ResponseEntity<Page<ProductResponse>> {
        return responseEntity(productService.findAll(pageable = this.retrievePageableParameter(parameters)))
    }

    @DeleteMapping("/{id}")
    override fun delete(@PathVariable id: UUID): ResponseEntity<Void> {
        productService.delete(id)
        return ResponseEntity.noContent().build()
    }

    @PatchMapping("/{id}/activate")
    override fun activate(@PathVariable id: UUID): ResponseEntity<ProductResponse> {
        return responseEntity(productService.activate(id))
    }

    @PatchMapping("/{id}/deactivate")
    override fun deactivate(@PathVariable id: UUID): ResponseEntity<ProductResponse> {
        return responseEntity(productService.deactivate(id))
    }
}
