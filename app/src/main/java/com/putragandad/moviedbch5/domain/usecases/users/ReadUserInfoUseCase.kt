package com.putragandad.moviedbch5.domain.usecases.users

import com.putragandad.moviedbch5.domain.repositories.users.UserAuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class ReadUserInfoUseCase(private val userAuthRepository: UserAuthRepository) {
    operator fun invoke(): Flow<Triple<String, String, String>> = combine(
        userAuthRepository.readAccountEmail,
        userAuthRepository.readAccountUserFullName,
        userAuthRepository.readAccountUsername
    ) { email, fullname, username ->
        Triple(email, fullname, username)
    }
}