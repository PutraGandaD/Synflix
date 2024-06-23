package com.putragandad.data.implementation.users

import com.putragandad.data.source.DataStoreSource
import com.putragandad.domain.repositories.users.UserAuthRepository
import kotlinx.coroutines.flow.Flow

class UserAuthRepositoryImpl(
    private val dataStoreSource: DataStoreSource
) : UserAuthRepository
{
    override suspend fun saveLoginStatus(status: Boolean) {
        dataStoreSource.saveLoginStatus(status)
    }

    override suspend fun registerUser(fullname: String, email: String, password: String) {
        dataStoreSource.registerUser(fullname, email, password)
    }

    override suspend fun saveAccountDetail(username: String, fullname: String, email: String) {
        dataStoreSource.saveAccountDetail(username, fullname, email)
    }

    override suspend fun setProfilePicture(uri: String) {
        dataStoreSource.saveProfilePictureUri(uri)
    }

    override suspend fun deleteAllPreferences() {
        dataStoreSource.deleteAllPreferences()
    }

    override val readLoginStatus: Flow<Boolean>
        get() = dataStoreSource.readLoginStatus

    override val readAccountUsername: Flow<String>
        get() = dataStoreSource.readAccountUsername

    override val readAccountUserFullName: Flow<String>
        get() = dataStoreSource.readAccountUserFullName

    override val readAccountEmail: Flow<String>
        get() = dataStoreSource.readAccountEmail

    override val readAccountPassword: Flow<String>
        get() = dataStoreSource.readAccountPassword

    override val readProfilePictureURI: Flow<String>
        get() = dataStoreSource.readProfilePictureURI

}