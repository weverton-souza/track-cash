package com.project.track.cash.service

import com.project.track.cash.domain.Seller
import com.project.track.cash.domain.User
import io.jsonwebtoken.Claims
import java.util.Date
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService

interface SecurityService : UserDetailsService {
    fun extractUserName(token: String): String

    fun generateToken(userDetails: User, seller: Seller, expiration: Date? = null): String

    fun isTokenValid(token: String, userDetails: UserDetails): Boolean

    fun extractSubject(token: String): String

    fun getAllClaimsFromToken(token: String?): Claims

    fun getUsernameFromToken(token: String?): String

    fun getExpirationDateFromToken(token: String): Date
}
