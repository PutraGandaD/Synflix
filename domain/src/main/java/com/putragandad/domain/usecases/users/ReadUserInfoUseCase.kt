package com.putragandad.domain.usecases.users

import com.putragandad.domain.models.users.AccountDetail
import com.putragandad.domain.repositories.users.UserAuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class ReadUserInfoUseCase(private val userAuthRepository: UserAuthRepository) {
    operator fun invoke(): Flow<AccountDetail> = combine(
        userAuthRepository.readAccountEmail,
        userAuthRepository.readAccountUserFullName,
        userAuthRepository.readAccountUsername,
        userAuthRepository.readProfilePictureURI
    ) { email, fullname, username, profileURI ->
        AccountDetail(email, fullname, username, profileURI)
    }
}