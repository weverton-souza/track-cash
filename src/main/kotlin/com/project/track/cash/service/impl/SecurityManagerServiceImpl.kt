package com.project.track.cash.service.impl

import com.project.track.cash.configuration.properties.SecurityProperties
import com.project.track.cash.domain.Seller
import com.project.track.cash.domain.User
import com.project.track.cash.enums.AccountStatus
import com.project.track.cash.model.SignInRequest
import com.project.track.cash.model.SignInResponse
import com.project.track.cash.repository.SellerRepository
import com.project.track.cash.repository.UserRepository
import com.project.track.cash.repository.extensions.findByEmailOrThrow
import com.project.track.cash.service.SecurityManagerService
import com.project.track.cash.service.SecurityService
import jakarta.transaction.Transactional
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date
import org.apache.tomcat.websocket.AuthenticationException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class SecurityManagerServiceImpl(
    private val passwordEncoder: PasswordEncoder,
    private val securityService: SecurityService,
    private val userRepository: UserRepository,
    private val sellerRepository: SellerRepository,
    private val securityProperties: SecurityProperties
) : SecurityManagerService {

    companion object {
        const val ALGORITHM_HMACSHA256 = "HmacSHA256"
        const val BEARER = "Bearer"
    }

    @Transactional
    override fun signIn(request: SignInRequest): SignInResponse {
        val user = this.userRepository.findByEmailOrThrow(request.email)

        val isUserOk = user.status == AccountStatus.ACTIVE || user.status == AccountStatus.PENDING_APPROVAL
        val hasMatch = this.passwordEncoder.matches(request.password, user.password)

        if (!hasMatch || !isUserOk) {
            throw AuthenticationException("Invalid credentials")
        }

        val seller = this.sellerRepository.findByTenantId(user.sellerTenantId!!)
            .orElseThrow {
                throw AuthenticationException("Seller not found")
            }

        return this.generateAndSaveNewTokens(user, seller)
    }

    override fun me(refreshToken: String): SignInResponse {
        val username = this.securityService.getUsernameFromToken(refreshToken)

        val user = this.userRepository.findByEmailOrThrow(username)

        if (!this.securityService.isTokenValid(refreshToken, user)) {
            throw AuthenticationException("Refresh Token not found")
        }

        val seller = this.sellerRepository.findByTenantId(user.sellerTenantId!!)
            .orElseThrow { throw AuthenticationException("Seller not found") }
        return this.generateAndSaveNewTokens(user, seller)
    }

    private fun generateAndSaveNewTokens(user: User, seller: Seller): SignInResponse {

        val expLocalDateTime = getRefreshTokenExpirationLocalDateTime()
        val expiration = getRefreshTokenExpirationDate(expLocalDateTime)

        val newToken = securityService.generateToken(user, seller, expiration)
        return buildJwtAuthenticationResponse(
            accessToken = newToken,
            userBasic = user
        )
    }

    private fun getRefreshTokenExpirationLocalDateTime() = LocalDateTime.now()
        .plusDays(7).withHour(23).withMinute(59).withSecond(59)

    private fun getRefreshTokenExpirationDate(expiration: LocalDateTime) =
        Date.from(expiration.atZone(ZoneId.systemDefault()).toInstant())

    private fun buildJwtAuthenticationResponse(
        accessToken: String,
        userBasic: User
    ) = SignInResponse(
        accessToken = accessToken,
        tokenType = BEARER,
        expiration = this.securityProperties.tokenExpiration
    )
}