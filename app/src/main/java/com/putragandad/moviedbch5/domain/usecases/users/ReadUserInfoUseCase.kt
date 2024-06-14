package com.putragandad.moviedbch5.domain.usecases.users

import com.putragandad.moviedbch5.domain.models.users.UserInfo
import com.putragandad.moviedbch5.domain.repositories.users.UserAuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class ReadUserInfoUseCase(private val userAuthRepository: UserAuthRepository) {
    operator fun invoke(): Flow<UserInfo> = combine(
        userAuthRepository.readAccountEmail,
        userAuthRepository.readAccountUserFullName,
        userAuthRepository.readAccountUsername,
        userAuthRepository.readProfilePictureURI
    ) { email, fullname, username, profileURI ->
        UserInfo(email, fullname, username, profileURI)
    }
}