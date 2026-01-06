package com.project.track.cash.extention

import com.project.track.cash.domain.AbstractEntity
import com.project.track.cash.extention.OptionalExtensions.orThrowNotFound
import java.util.Optional
import java.util.UUID
import org.springframework.data.jpa.repository.JpaRepository

inline fun <reified T : AbstractEntity> JpaRepository<T, UUID>.findByIdOrThrow(id: UUID): T =
    findById(id).orThrowNotFound()

inline fun <reified T : AbstractEntity> JpaRepository<T, UUID>.existOrThrow(id: UUID): Boolean {
    if (!existsById(id)) {
        throw RuntimeException("HTTP_4XX_404_NOT_FOUND")
    }
    return true
}

inline fun <reified T : AbstractEntity> JpaRepository<T, UUID>.deleteByIdOrThrow(id: UUID) {
    if (!existsById(id)) {
        throw RuntimeException("HTTP_4XX_404_NOT_FOUND")
    }
    deleteById(id)
}

inline fun <reified T : AbstractEntity> JpaRepository<T, UUID>.findByUUIDOrThrow(id: UUID): T = findByIdOrThrow(id)

object OptionalExtensions {
    fun <T> Optional<T>.orThrowNotFound(message: String = "HTTP_4XX_404_NOT_FOUND"): T = orElseThrow { RuntimeException(message) }

    fun <T> Optional<T>.orNull(): T? = orElse(null)
}
