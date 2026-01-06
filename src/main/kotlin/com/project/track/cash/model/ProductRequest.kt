package com.project.track.cash.resources.request

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.math.BigDecimal

@Schema(description = "Requisição para criar ou atualizar um produto")
data class ProductRequest(

    @field:NotBlank(message = "Nome é obrigatório")
    @field:Size(min = 2, max = 255, message = "Nome deve ter entre 2 e 255 caracteres")
    @Schema(
        description = "Nome do produto",
        example = "Notebook Gamer",
        minLength = 2,
        maxLength = 255,
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    val name: String,

    @field:Size(max = 1000, message = "Descrição deve ter no máximo 1000 caracteres")
    @Schema(
        description = "Descrição detalhada do produto",
        example = "Notebook com processador Intel i7, 16GB RAM, SSD 512GB e RTX 4060",
        maxLength = 1000,
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    val description: String? = null,

    @field:DecimalMin(value = "0.0", inclusive = false, message = "Preço deve ser maior que zero")
    @Schema(
        description = "Preço do produto em reais",
        example = "4599.90",
        minimum = "0.01",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    val price: BigDecimal,

    @Schema(
        description = "Indica se o produto está ativo para venda",
        example = "true",
        defaultValue = "true",
        requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    val active: Boolean = true
)
