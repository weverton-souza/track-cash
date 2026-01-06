package com.project.track.cash.utils

import java.util.UUID

data class Context(private val tenantId: UUID?, private val userId: UUID?) {
    fun getTenantId(): UUID? = tenantId
    fun getUserId(): UUID? = userId
}