package com.putragandad.moviedbch5.domain.usecases.users

import com.putragandad.moviedbch5.domain.repositories.users.UserAuthRepository

class UpdateUserInfoUseCase(private val userAuthRepository: UserAuthRepository) {
    operator suspend fun invoke(username: String, fullname: String, email: String) {
        userAuthRepository.saveAccountDetail(username, fullname, email)
    }
}