package com.project.track.cash.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.SQLRestriction
import java.math.BigDecimal
import java.util.UUID

@Entity
@Table(name = "product")
@SQLDelete(sql = "UPDATE product SET deleted = true WHERE id = ?")
@SQLRestriction("deleted = false")
data class Product(
    @Id
    @field:Column
    override var id: UUID = UUID.randomUUID(),

    @field:Column
    var name: String = "",

    @field:Column
    var description: String? = null,

    @field:Column
    var price: BigDecimal = BigDecimal.ZERO,

    @field:Column
    var active: Boolean = true

) : AbstractEntity(id = id)
