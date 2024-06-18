package com.putragandad.moviedbch5.domain.usecases.users

import com.putragandad.moviedbch5.domain.repositories.users.UserAuthRepository

class UserLogoutUseCase(private val userAuthRepository: UserAuthRepository) {
    operator suspend fun invoke() {
        userAuthRepository.saveLoginStatus(false)
//        userAuthRepository.deleteAllPreferences()
    }
}