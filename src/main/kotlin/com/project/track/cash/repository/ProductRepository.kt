package com.project.track.cash.repository

import com.project.track.cash.domain.Product
import java.util.UUID
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, UUID>