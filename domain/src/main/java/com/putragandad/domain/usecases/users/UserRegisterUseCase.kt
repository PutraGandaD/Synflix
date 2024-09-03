package com.putragandad.synflix.domain.usecases.users

import com.putragandad.synflix.domain.repositories.users.UserAuthRepository

class UserRegisterUseCase(private val userAuthRepository: UserAuthRepository) {
    suspend operator fun invoke(fullname: String, email: String, password: String) {
        userAuthRepository.deleteAllPreferences()
        userAuthRepository.registerUser(fullname, email, password)
    }
}