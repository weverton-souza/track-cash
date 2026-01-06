package com.project.track.cash.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "seller", schema = "public")
data class Seller(
    @Id
    @field:Column
    override var id: UUID = UUID.randomUUID(),

    @field:Column
    var name: String = "",

    @field:Column
    var shortName: String = "",

    @field:Column
    var tenantId: UUID? = null
) : AbstractEntity(id = id)
