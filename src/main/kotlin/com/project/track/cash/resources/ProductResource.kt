package com.project.track.cash.resources

import com.project.track.cash.model.ProductResponse
import com.project.track.cash.resources.request.ProductRequest
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.Parameters
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import java.util.UUID
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam

@Tag(
    name = "Product Resources",
    description = "Recursos para gerenciamento de produtos"
)
interface ProductResource : BaseResource {

    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Produto criado com sucesso",
                content = [
                    Content(
                        mediaType = "application/json",
                        schema = Schema(implementation = ProductResponse::class)
                    )
                ]
            ),
            ApiResponse(responseCode = "400", description = "Dados inválidos"),
            ApiResponse(responseCode = "401", description = "Não autorizado")
        ]
    )
    @Operation(
        summary = "Criar produto",
        description = "Cria um novo produto para o tenant autenticado"
    )
    fun create(
        @Parameter(description = "Dados do produto")
        @Valid @RequestBody request: ProductRequest
    ): ResponseEntity<ProductResponse>

    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Produto atualizado com sucesso",
                content = [
                    Content(
                        mediaType = "application/json",
                        schema = Schema(implementation = ProductResponse::class)
                    )
                ]
            ),
            ApiResponse(responseCode = "400", description = "Dados inválidos"),
            ApiResponse(responseCode = "404", description = "Produto não encontrado")
        ]
    )
    @Operation(
        summary = "Atualizar produto",
        description = "Atualiza um produto existente pelo ID"
    )
    fun update(
        @Parameter(description = "ID do produto")
        @PathVariable id: UUID,
        @Parameter(description = "Dados do produto")
        @Valid @RequestBody request: ProductRequest
    ): ResponseEntity<ProductResponse>

    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Produto encontrado",
                content = [
                    Content(
                        mediaType = "application/json",
                        schema = Schema(implementation = ProductResponse::class)
                    )
                ]
            ),
            ApiResponse(responseCode = "404", description = "Produto não encontrado")
        ]
    )
    @Operation(
        summary = "Buscar produto por ID",
        description = "Retorna um produto pelo seu ID"
    )
    fun findById(
        @Parameter(description = "ID do produto")
        @PathVariable id: UUID
    ): ResponseEntity<ProductResponse>

    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Lista de produtos retornada com sucesso"
            )
        ]
    )
    @Operation(
        summary = "Listar produtos",
        description = "Retorna todos os produtos do tenant de forma paginada"
    )
    @Parameters(
        value = [
            Parameter(
                name = "pageNumber",
                `in` = ParameterIn.QUERY,
                description = "Número da página (iniciando em 0)",
                example = "0",
                required = false
            ),
            Parameter(
                name = "pageSize",
                `in` = ParameterIn.QUERY,
                description = "Quantidade de itens por página",
                example = "15",
                required = false
            ),
            Parameter(
                name = "parameters",
                `in` = ParameterIn.QUERY,
                description = "Parâmetros adicionais para a busca",
                required = false,
                hidden = true
            )
        ]
    )
    fun findAll(
        @Parameter(
            description = "Parâmetros de filtro e paginação",
            hidden = true
        )
        @RequestParam parameters: Map<String, Any>
    ): ResponseEntity<Page<ProductResponse>>

    @ApiResponses(
        value = [
            ApiResponse(responseCode = "204", description = "Produto excluído com sucesso"),
            ApiResponse(responseCode = "404", description = "Produto não encontrado")
        ]
    )
    @Operation(
        summary = "Excluir produto",
        description = "Exclui um produto pelo ID (soft delete)"
    )
    fun delete(
        @Parameter(description = "ID do produto")
        @PathVariable id: UUID
    ): ResponseEntity<Void>

    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Produto ativado com sucesso",
                content = [
                    Content(
                        mediaType = "application/json",
                        schema = Schema(implementation = ProductResponse::class)
                    )
                ]
            ),
            ApiResponse(responseCode = "404", description = "Produto não encontrado")
        ]
    )
    @Operation(
        summary = "Ativar produto",
        description = "Ativa um produto para venda"
    )
    fun activate(
        @Parameter(description = "ID do produto")
        @PathVariable id: UUID
    ): ResponseEntity<ProductResponse>

    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Produto desativado com sucesso",
                content = [
                    Content(
                        mediaType = "application/json",
                        schema = Schema(implementation = ProductResponse::class)
                    )
                ]
            ),
            ApiResponse(responseCode = "404", description = "Produto não encontrado")
        ]
    )
    @Operation(
        summary = "Desativar produto",
        description = "Desativa um produto para venda"
    )
    fun deactivate(
        @Parameter(description = "ID do produto")
        @PathVariable id: UUID
    ): ResponseEntity<ProductResponse>
}
