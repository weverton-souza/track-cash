package com.project.track.cash.configuration.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding

@ConfigurationProperties(prefix = "application.track-cash.security")
data class SecurityProperties @ConstructorBinding constructor(
    val tokenExpiration: Long,
    val refreshTokenExpiration: Long,
    val jwtSigningKey: String,
    val urlSigningKey: String,
    val iterationCount: Int
)
