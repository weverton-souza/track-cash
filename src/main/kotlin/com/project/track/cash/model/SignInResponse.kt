package com.project.track.cash.model

import com.fasterxml.jackson.annotation.JsonInclude
import io.swagger.v3.oas.annotations.media.Schema

@Schema(
    name = "SignIn Response",
    description = "Response object containing the JWT access token, refresh token, and additional user information after successful authentication"
)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class SignInResponse(
    @field:Schema(
        description = "JWT token for the authenticated session",
        example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9" +
                ".eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ" +
                ".SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c"
    )
    val accessToken: String,

    @field:Schema(description = "The expiration time of the JWT access token in milliseconds", example = "3600000")
    val expiration: Long,

    @field:Schema(description = "Type of the token, e.g., Bearer", example = "Bearer")
    val tokenType: String
)
