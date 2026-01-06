package com.project.track.cash.repository

import com.project.track.cash.domain.User
import java.util.Optional
import java.util.UUID
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, UUID> {
    companion object {
        const val FIND_BY_EMAIL = """
                SELECT u.id,
                   u.created_at,
                   u.created_by,
                   u.deleted,
                   u.email,
                   u.seller_tenant_id,
                   u.name,
                   u.password,
                   u.status,
                   u.type,
                   u.updated_at,
                   u.updated_by,
                   u.verified
                FROM "public"."user" u
                WHERE u.email=:email
            """
    }
    @Query(value = FIND_BY_EMAIL, nativeQuery = true)
    fun findByEmail(email: String): Optional<User>
}
