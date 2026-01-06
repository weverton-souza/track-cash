package com.project.track.cash.repository

import com.project.track.cash.domain.Seller
import java.util.Optional
import java.util.UUID
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SellerRepository : JpaRepository<Seller, UUID> {
    fun findByTenantId(tenantId: UUID): Optional<Seller>

    fun <T> findByTenantId(tenantId: UUID, type: Class<T>): Optional<T>
}
