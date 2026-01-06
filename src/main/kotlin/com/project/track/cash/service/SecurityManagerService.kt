package com.project.track.cash.service

import com.project.track.cash.model.SignInRequest
import com.project.track.cash.model.SignInResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory

interface SecurityManagerService {
    companion object {
        const val BEARER = "Bearer "
        val LOGGER: Logger = LoggerFactory.getLogger(SecurityManagerService::class.java)
    }

    fun signIn(request: SignInRequest): SignInResponse

    fun me(refreshToken: String): SignInResponse
}