package com.putragandad.moviedbch5.domain.usecases.users

import com.putragandad.moviedbch5.domain.repositories.users.UserAuthRepository
import kotlinx.coroutines.flow.Flow

class CheckLoginUseCase(private val userAuthRepository: UserAuthRepository) {
    operator fun invoke(): Flow<Boolean> {
        return userAuthRepository.readLoginStatus
    }
}