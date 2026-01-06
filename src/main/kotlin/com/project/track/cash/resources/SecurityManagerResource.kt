package com.project.track.cash.resources

import com.project.track.cash.model.SignInRequest
import com.project.track.cash.model.SignInResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader

@Tag(
    name = "Security Manager Resources",
    description = "Recursos para gerenciar a segurança da aplicação"
)
interface SecurityManagerResource : BaseResource {
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Aluno autenticado com sucesso",
                content = [
                    Content(
                        mediaType = "application/json",
                        schema = Schema(implementation = SignInResponse::class)
                    )
                ]
            )
        ]
    )
    @Operation(
        summary = "Serviço para autenticação de aluno",
        description = "Este endpoint permite a autenticação de um aluno fornecendo as credenciais no corpo da requisição."
    )
    fun studentSignIn(
        @Parameter(description = "Credenciais para autenticação do aluno")
        @Valid @RequestBody
        request: SignInRequest
    ): ResponseEntity<SignInResponse>

    fun me(@RequestHeader(name = "refresh-token") refreshToken: String): ResponseEntity<SignInResponse>
}