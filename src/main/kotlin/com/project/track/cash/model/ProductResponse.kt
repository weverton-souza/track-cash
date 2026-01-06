package com.project.track.cash.model

import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

@Schema(description = "Resposta com dados do produto")
data class ProductResponse(

    @Schema(
        description = "Identificador único do produto",
        example = "550e8400-e29b-41d4-a716-446655440000"
    )
    val id: UUID,

    @Schema(
        description = "Nome do produto",
        example = "Notebook Gamer"
    )
    val name: String,

    @Schema(
        description = "Descrição detalhada do produto",
        example = "Notebook com processador Intel i7, 16GB RAM, SSD 512GB e RTX 4060",
        nullable = true
    )
    val description: String?,

    @Schema(
        description = "Preço do produto em reais",
        example = "4599.90"
    )
    val price: BigDecimal,

    @Schema(
        description = "Indica se o produto está ativo para venda",
        example = "true"
    )
    val active: Boolean,

    @Schema(
        description = "Data e hora de criação do registro",
        example = "2026-01-05T17:30:00"
    )
    val createdAt: LocalDateTime?,

    @Schema(
        description = "Data e hora da última atualização",
        example = "2026-01-05T18:45:00",
        nullable = true
    )
    val updatedAt: LocalDateTime?
)