package com.project.track.cash.service.impl

import com.project.track.cash.repository.UserRepository
import com.project.track.cash.repository.extensions.findByEmailOrThrow
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails = this.userRepository.findByEmailOrThrow(username)
}