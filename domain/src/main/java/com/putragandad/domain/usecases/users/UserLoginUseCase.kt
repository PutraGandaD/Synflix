package com.putragandad.domain.usecases.users

import com.putragandad.domain.repositories.users.UserAuthRepository
import kotlinx.coroutines.flow.firstOrNull

class UserLoginUseCase(private val userAuthRepository: UserAuthRepository) {
    suspend operator fun invoke(email: String, password: String)  {
        val registeredEmail = userAuthRepository.readAccountEmail.firstOrNull()
        val registeredPassword = userAuthRepository.readAccountPassword.firstOrNull()

        if (email == registeredEmail && password == registeredPassword) {
            userAuthRepository.saveLoginStatus(true)
        } else {
            userAuthRepository.saveLoginStatus(false)
        }
    }
}