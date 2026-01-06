package com.project.track.cash.configuration

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.project.track.cash.service.SecurityService
import com.project.track.cash.utils.Context
import com.project.track.cash.utils.ContextHolder
import io.jsonwebtoken.Claims
import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import java.util.UUID
import org.springframework.stereotype.Component

data class Claim(

    @SerializedName(value = "USER_ID")
    val userId: String,

    @SerializedName("USER_NAME")
    val username: String,

    @SerializedName("TENANT_ID")
    val tenantId: String
)

@Component
class MultiTenantFilter(
    private val securityService: SecurityService
) : Filter {

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val req = request as HttpServletRequest

        val accessToken = req.getHeader(TenancyConstant.AUTHORIZATION)
        var tenantUUID: UUID? = null
        var userUUID: UUID? = null

        if (!accessToken.isNullOrEmpty()) {
            val claims: Claims = securityService.getAllClaimsFromToken(accessToken.substring(7))

            val claim: Claim = Gson().fromJson(claims["sub"].toString(), Claim::class.java)

            tenantUUID = UUID.fromString(claim.tenantId)
            userUUID = UUID.fromString(claim.userId)
        }

        req.setAttribute(TenancyConstant.TENANT_ID, tenantUUID)
        req.setAttribute(TenancyConstant.USER_KEY, userUUID)

        ContextHolder.context = Context(tenantUUID, userUUID)

        try {
            chain.doFilter(request, response)
        } finally {
            ContextHolder.clear()
        }
    }
}
