package com.putragandad.data.source.local

import com.putragandad.data.source.datastore.DataStorePreference
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val dataStore: DataStorePreference) {
    suspend fun saveLoginStatus(status: Boolean) {
        dataStore.saveLoginStatus(status)
    }

    suspend fun registerUser(fullname: String, email: String, password: String) {
        dataStore.registerUser(fullname, email, password)
    }

    suspend fun saveAccountDetail(username: String, fullname: String, email: String) {
        dataStore.saveAccountDetail(username, fullname, email)
    }

    suspend fun setProfilePicture(uri: String) {
        dataStore.saveProfilePictureUri(uri)
    }

    suspend fun deleteAllPreferences() {
        dataStore.deleteAllPreferences()
    }

    val readLoginStatus: Flow<Boolean>
        get() = dataStore.readLoginStatus

    val readAccountUsername: Flow<String>
        get() = dataStore.readAccountUsername

    val readAccountUserFullName: Flow<String>
        get() = dataStore.readAccountUserFullName

    val readAccountEmail: Flow<String>
        get() = dataStore.readAccountEmail

    val readAccountPassword: Flow<String>
        get() = dataStore.readAccountPassword

    val readProfilePictureURI: Flow<String>
        get() = dataStore.readProfilePictureURI
}