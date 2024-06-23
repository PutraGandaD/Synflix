package com.putragandad.testdomain.data.repository

import com.putragandad.domain.repositories.users.UserAuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeUserRepository : UserAuthRepository {
    private var _loginStatus = false
    private var _email = ""
    private var _fullname = ""
    private var _password = ""

    override suspend fun saveLoginStatus(status: Boolean) {
        _loginStatus = status
    }

    override suspend fun registerUser(fullname: String, email: String, password: String) {
        _fullname = fullname
        _email = email
        _password = password
    }

    override suspend fun saveAccountDetail(username: String, fullname: String, email: String) {
        TODO("Not yet implemented")
    }

    override suspend fun setProfilePicture(uri: String) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllPreferences() {
        _email = ""
        _fullname = ""
        _password = ""
    }

    override val readLoginStatus: Flow<Boolean>
        get() = flow { emit(_loginStatus) }
    override val readAccountUsername: Flow<String>
        get() = TODO("Not yet implemented")
    override val readAccountUserFullName: Flow<String>
        get() = flow { emit(_fullname) }
    override val readAccountEmail: Flow<String>
        get() = flow { emit(_email) }
    override val readAccountPassword: Flow<String>
        get() = flow { emit(_password) }
    override val readProfilePictureURI: Flow<String>
        get() = TODO("Not yet implemented")
}