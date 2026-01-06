package com.project.track.cash.service.impl

import com.google.gson.Gson
import com.project.track.cash.configuration.properties.SecurityProperties
import com.project.track.cash.domain.Seller
import com.project.track.cash.domain.User
import com.project.track.cash.repository.UserRepository
import com.project.track.cash.repository.extensions.findByEmailOrThrow
import com.project.track.cash.service.SecurityService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import java.security.Key
import java.util.Date
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service

@Service
class SecurityServiceImpl(
    private val gson: Gson,
    private val userRepository: UserRepository,
    private val securityProperties: SecurityProperties
) : SecurityService {
    override fun extractUserName(token: String): String = getUsernameFromToken(token)

    override fun generateToken(userDetails: User, seller: Seller, expiration: Date?): String = generateToken(HashMap(), userDetails, seller, expiration)

    override fun isTokenValid(token: String, userDetails: UserDetails): Boolean = extractUserName(token) == userDetails.username && !isTokenExpired(token)

    private fun generateToken(
        extraClaims: Map<String, String>,
        user: User,
        seller: Seller,
        expiration: Date? = null
    ): String {
        val subject = mapOf(
            "USER_ID" to user.id.toString(),
            "USER_NAME" to user.email,
            "TENANT_ID" to seller.tenantId.toString()
        )

        val expirationDate = expiration ?: Date(System.currentTimeMillis() + securityProperties.tokenExpiration)

        return Jwts.builder()
            .setClaims(extraClaims)
            .setSubject(gson.toJson(subject))
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(expirationDate)
            .signWith(getSigningKey(), SignatureAlgorithm.HS256)
            .compact()
    }

    private fun isTokenExpired(token: String) = extractExpiration(token).before(Date())

    private fun extractExpiration(token: String): Date {
        return getExpirationDateFromToken(token)
    }

    override fun extractSubject(token: String): String {
        return try {
            Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .body
                .subject
        } catch (_: ExpiredJwtException) {
            throw RuntimeException("HTTP_4XX_401_UNAUTHORIZED")
        } catch (_: JwtException) {
            throw RuntimeException("HTTP_4XX_401_UNAUTHORIZED")
        } catch (ex: Exception) {
            throw RuntimeException("Erro ao processar o token JWT", ex)
        }
    }

    private fun getSigningKey(): Key {
        return Keys.hmacShaKeyFor(securityProperties.jwtSigningKey.toByteArray())
    }

    override fun getAllClaimsFromToken(token: String?): Claims {
        val hmacShaKey = Keys.hmacShaKeyFor(securityProperties.jwtSigningKey.toByteArray())
        return Jwts.parserBuilder()
            .setSigningKey(hmacShaKey)
            .build()
            .parseClaimsJws(token)
            .body
    }

    override fun getUsernameFromToken(token: String?): String {
        val subject = getAllClaimsFromToken(token).subject
        val subjectMap: HashMap<String, String> =
            gson.fromJson<HashMap<String, String>>(subject.toString(), HashMap::class.java)
        return subjectMap["USER_NAME"].toString()
    }

    override fun getExpirationDateFromToken(token: String): Date {
        return getAllClaimsFromToken(token).expiration
    }
    override fun loadUserByUsername(username: String): UserDetails {
        return userRepository.findByEmailOrThrow(username)
    }
}
