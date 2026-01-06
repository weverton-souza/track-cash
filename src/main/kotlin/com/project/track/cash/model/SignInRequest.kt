package com.project.track.cash.model

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

@Schema(
    name = "SignIn Request",
    description = "Objeto de requisição para autenticação de um usuário"
)
data class SignInRequest(

    @field:Size(max = 50, message = "APPLICATION_VALIDATION_CONSTRAINTS_SIZE_FIELD_MESSAGE")
    @field:NotBlank(message = "APPLICATION_VALIDATION_CONSTRAINTS_NOTBLANK_FIELD_MESSAGE")
    @field:Schema(description = "Email do usuário", example = "admin@system.com", required = true)
    val email: String,

    @field:Size(max = 50, message = "APPLICATION_VALIDATION_CONSTRAINTS_SIZE_FIELD_MESSAGE")
    @field:NotBlank(message = "APPLICATION_VALIDATION_CONSTRAINTS_NOTBLANK_FIELD_MESSAGE")
    @field:Schema(description = "Senha do usuário", example = "123", required = true)
    val password: String,

    @field:Schema(description = "It says if user wanna keep logged in", example = "true", required = false)
    val keepLoggedIn: Boolean = false,
)
