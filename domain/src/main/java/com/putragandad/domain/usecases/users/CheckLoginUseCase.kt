package com.putragandad.domain.usecases.users

import com.putragandad.domain.repositories.users.UserAuthRepository
import kotlinx.coroutines.flow.Flow

class CheckLoginUseCase(private val userAuthRepository: UserAuthRepository) {
    operator fun invoke(): Flow<Boolean> {
        return userAuthRepository.readLoginStatus
    }
}