package com.project.track.cash.repository.extensions

import com.project.track.cash.domain.User
import com.project.track.cash.extention.OptionalExtensions.orThrowNotFound
import com.project.track.cash.repository.UserRepository

fun UserRepository.findByEmailOrThrow(email: String): User = findByEmail(email).orThrowNotFound()