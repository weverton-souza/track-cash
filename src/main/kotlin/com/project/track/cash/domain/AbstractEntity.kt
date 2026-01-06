package com.project.track.cash.domain

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.MappedSuperclass
import java.time.LocalDateTime
import java.util.UUID
import org.hibernate.annotations.SQLRestriction
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@MappedSuperclass
@SQLRestriction("deleted=false")
@EntityListeners(AuditingEntityListener::class)
class AbstractEntity(
    @Id
    @field:Column(name = "id")
    open var id: UUID,

    @field:Column(name = "deleted")
    open var deleted: Boolean = false,

    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @field:JoinColumn(name = "created_by", updatable = false)
    open var createdBy: User? = null,

    @CreatedDate
    @field:Column(name = "created_at", updatable = false)
    open var createdAt: LocalDateTime? = null,

    @LastModifiedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @field:JoinColumn(name = "updated_by")
    open var updatedBy: User? = null,

    @LastModifiedDate
    @field:Column(name = "updated_at")
    open var updatedAt: LocalDateTime? = null
)