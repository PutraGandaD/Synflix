package com.putragandad.moviedbch5.domain.usecases.users

import com.putragandad.moviedbch5.domain.repositories.users.UserAuthRepository

class UserRegisterUseCase(private val userAuthRepository: UserAuthRepository) {
    operator suspend fun invoke(fullname: String, email: String, password: String) {
        userAuthRepository.registerUser(fullname, email, password)
    }
}