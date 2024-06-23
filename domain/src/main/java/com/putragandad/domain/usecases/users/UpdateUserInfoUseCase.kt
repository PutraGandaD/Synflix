package com.putragandad.domain.usecases.users

import com.putragandad.domain.repositories.users.UserAuthRepository

class UpdateUserInfoUseCase(private val userAuthRepository: UserAuthRepository) {
    suspend operator fun invoke(username: String, fullname: String, email: String) {
        userAuthRepository.saveAccountDetail(username, fullname, email)
    }
}