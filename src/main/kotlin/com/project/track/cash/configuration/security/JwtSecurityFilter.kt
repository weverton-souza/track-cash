package com.project.track.cash.configuration.security

import com.project.track.cash.service.SecurityService
import com.project.track.cash.service.impl.UserServiceImpl
import io.jsonwebtoken.JwtException
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.web.filter.OncePerRequestFilter

@Configuration
class JwtSecurityFilter(
    private val securityService: SecurityService,
    private val userServiceImpl: UserServiceImpl
) : OncePerRequestFilter() {

    companion object {
        val LOGGER: Logger = LoggerFactory.getLogger(JwtSecurityFilter::class.java)
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            val requestHeader = request.getHeader("Authorization")

            if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
                val token = requestHeader.substring(7)
                val userName = securityService.extractUserName(token)

                if (SecurityContextHolder.getContext().authentication == null) {
                    val user = userServiceImpl.loadUserByUsername(userName)

                    if (securityService.isTokenValid(token, user)) {
                        val authToken = UsernamePasswordAuthenticationToken(user, null, user.authorities)
                        authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                        SecurityContextHolder.getContext().authentication = authToken
                    }
                }
            }
        } catch (e: JwtException) {
            LOGGER.error("class=JwtSecurityFilter, method=doFilterInternal, message=Security error: error={}", e.message)
        }

        filterChain.doFilter(request, response)
    }
}