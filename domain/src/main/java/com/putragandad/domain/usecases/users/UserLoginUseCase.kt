package com.putragandad.moviedbch5.domain.usecases.users

import android.util.Log
import com.putragandad.moviedbch5.domain.repositories.users.UserAuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow

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