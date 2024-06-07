package com.putragandad.moviedbch5.utils.prefdatastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(PrefDataStoreConstant.USER_CREDENTIAL)

class PrefDataStoreManager(private val context: Context) {

    suspend fun saveLoginStatus(status: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[PrefDataStoreConstant.ISLOGIN] = status
        }
    }

    suspend fun registerUser(fullname: String, email: String, password: String) {
        context.dataStore.edit { preferences ->
            preferences[PrefDataStoreConstant.USER_FULLNAME] = fullname
            preferences[PrefDataStoreConstant.USER_EMAIL] = email
            preferences[PrefDataStoreConstant.USER_PASSWORD] = password
        }
    }

    suspend fun saveAccountDetail(userName: String, fullname: String, email: String) {
        context.dataStore.edit { preferences ->
            preferences[PrefDataStoreConstant.USERNAME] = userName
            preferences[PrefDataStoreConstant.USER_FULLNAME] = fullname
            preferences[PrefDataStoreConstant.USER_EMAIL] = email
        }
    }

    val readLoginStatus: Flow<Boolean> = context.dataStore.data
        .catch { exception ->
            // dataStore.data throws an IOException when an error is encountered when reading data
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map {preferences ->
            preferences[PrefDataStoreConstant.ISLOGIN] ?: false
        }

    val readAccountUsername: Flow<String> = context.dataStore.data
        .catch { exception ->
            // dataStore.data throws an IOException when an error is encountered when reading data
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map {preferences ->
            preferences[PrefDataStoreConstant.USERNAME] ?: ""
        }

    val readAccountUserFullName: Flow<String> = context.dataStore.data
        .catch { exception ->
            // dataStore.data throws an IOException when an error is encountered when reading data
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map {preferences ->
            preferences[PrefDataStoreConstant.USER_FULLNAME] ?: ""
        }

    val readAccountEmail: Flow<String> = context.dataStore.data
        .catch { exception ->
            // dataStore.data throws an IOException when an error is encountered when reading data
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map {preferences ->
            preferences[PrefDataStoreConstant.USER_EMAIL] ?: ""
        }

    val readAccountPassword: Flow<String> = context.dataStore.data
        .catch { exception ->
            // dataStore.data throws an IOException when an error is encountered when reading data
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map {preferences ->
            preferences[PrefDataStoreConstant.USER_PASSWORD] ?: ""
        }
}