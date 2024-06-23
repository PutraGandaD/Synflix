package com.putragandad.domain.usecases.users

import com.putragandad.domain.repositories.users.UserAuthRepository

class SetProfilePictureUseCase(private val userAuthRepository: UserAuthRepository) {
    suspend operator fun invoke(uri: String) {
        userAuthRepository.setProfilePicture(uri)
    }
}