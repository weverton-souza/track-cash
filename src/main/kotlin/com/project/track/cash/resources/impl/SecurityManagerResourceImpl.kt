package com.project.track.cash.resources.impl

import com.project.track.cash.model.SignInRequest
import com.project.track.cash.model.SignInResponse
import com.project.track.cash.resources.SecurityManagerResource
import com.project.track.cash.service.SecurityManagerService
import jakarta.validation.Valid
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Validated
@RestController
@CrossOrigin
@RequestMapping("/security-manager")
class SecurityManagerResourceImpl(
    private val securityManagerService: SecurityManagerService,
) : SecurityManagerResource {

    companion object {
        val LOGGER: Logger = LoggerFactory.getLogger(SecurityManagerResourceImpl::class.java)
    }

    @PostMapping("sign-in")
    override fun studentSignIn(@RequestBody @Valid request: SignInRequest): ResponseEntity<SignInResponse> {
        return this.responseEntity(this.securityManagerService.signIn(request))
    }

    @PostMapping("me")
    override fun me(@RequestHeader("refresh-token") refreshToken: String): ResponseEntity<SignInResponse> {
        return this.responseEntity(this.securityManagerService.me(refreshToken))
    }
}