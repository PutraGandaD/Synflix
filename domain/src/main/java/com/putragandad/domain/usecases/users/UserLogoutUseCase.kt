package com.putragandad.synflix.domain.usecases.users

import com.putragandad.synflix.domain.repositories.users.UserAuthRepository

class UserLogoutUseCase(private val userAuthRepository: UserAuthRepository) {
    suspend operator fun invoke() {
        userAuthRepository.saveLoginStatus(false)
//        userAuthRepository.deleteAllPreferences()
    }
}