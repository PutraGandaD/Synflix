package com.putragandad.moviedbch5.domain.usecases.users

import com.putragandad.moviedbch5.domain.repositories.users.UserAuthRepository

class SetProfilePictureUseCase(private val userAuthRepository: UserAuthRepository) {
    operator suspend fun invoke(uri: String) {
        userAuthRepository.setProfilePicture(uri)
    }
}