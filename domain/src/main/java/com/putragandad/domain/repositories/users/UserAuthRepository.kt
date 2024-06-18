package com.putragandad.moviedbch5.domain.repositories.users

import kotlinx.coroutines.flow.Flow

interface UserAuthRepository {
    suspend fun saveLoginStatus(status: Boolean)
    suspend fun registerUser(fullname: String, email: String, password: String)
    suspend fun saveAccountDetail(username: String, fullname: String, email: String)
    suspend fun setProfilePicture(uri: String)
    suspend fun deleteAllPreferences()
    val readLoginStatus : Flow<Boolean>
    val readAccountUsername : Flow<String>
    val readAccountUserFullName : Flow<String>
    val readAccountEmail : Flow<String>
    val readAccountPassword : Flow<String>
    val readProfilePictureURI : Flow<String>
}