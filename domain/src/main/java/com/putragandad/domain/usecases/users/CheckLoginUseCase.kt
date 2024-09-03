package com.putragandad.synflix.domain.usecases.users

import com.putragandad.synflix.domain.repositories.users.UserAuthRepository
import kotlinx.coroutines.flow.Flow

class CheckLoginUseCase(private val userAuthRepository: UserAuthRepository) {
    operator fun invoke(): Flow<Boolean> {
        return userAuthRepository.readLoginStatus
    }
}