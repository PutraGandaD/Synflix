package com.putragandad.moviedbch5.utils

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PrefDataStoreConstant {
    const val USER_CREDENTIAL = "synflix_login_credentials"
    val ISLOGIN = booleanPreferencesKey("is_login")
    val USER_EMAIL = stringPreferencesKey("user_email")
    val USER_PASSWORD = stringPreferencesKey("user_password")
    val USER_FULLNAME = stringPreferencesKey("user_fullname")
    val USERNAME = stringPreferencesKey("user_name")
}