package com.putragandad.synflix.domain.usecases.users

import com.putragandad.synflix.domain.models.users.AccountDetail
import com.putragandad.synflix.domain.repositories.users.UserAuthRepository
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