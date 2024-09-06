package com.putragandad.synflix.data.implementation.users

import com.putragandad.data.source.datastore.DataStorePreference
import com.putragandad.data.source.local.LocalDataSource
import com.putragandad.synflix.domain.repositories.users.UserAuthRepository
import kotlinx.coroutines.flow.Flow

class UserAuthRepositoryImpl(
    private val localDataSource: LocalDataSource
) : UserAuthRepository
{
    override suspend fun saveLoginStatus(status: Boolean) {
        localDataSource.saveLoginStatus(status)
    }

    override suspend fun registerUser(fullname: String, email: String, password: String) {
        localDataSource.registerUser(fullname, email, password)
    }

    override suspend fun saveAccountDetail(username: String, fullname: String, email: String) {
        localDataSource.saveAccountDetail(username, fullname, email)
    }

    override suspend fun setProfilePicture(uri: String) {
        localDataSource.setProfilePicture(uri)
    }

    override suspend fun deleteAllPreferences() {
        localDataSource.deleteAllPreferences()
    }

    override val readLoginStatus: Flow<Boolean>
        get() = localDataSource.readLoginStatus

    override val readAccountUsername: Flow<String>
        get() = localDataSource.readAccountUsername

    override val readAccountUserFullName: Flow<String>
        get() = localDataSource.readAccountUserFullName

    override val readAccountEmail: Flow<String>
        get() = localDataSource.readAccountEmail

    override val readAccountPassword: Flow<String>
        get() = localDataSource.readAccountPassword

    override val readProfilePictureURI: Flow<String>
        get() = localDataSource.readProfilePictureURI

}