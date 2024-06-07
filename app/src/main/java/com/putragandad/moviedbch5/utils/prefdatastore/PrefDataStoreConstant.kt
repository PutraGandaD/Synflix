package com.putragandad.moviedbch5.utils.prefdatastore

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PrefDataStoreConstant {
    const val USER_CREDENTIAL = "synflix_login_credentials"
    const val ISLOGIN = booleanPreferencesKey("is_login")
    const val USER_EMAIL = stringPreferencesKey("user_email")
    const val USER_PASSWORD = stringPreferencesKey("user_password")
    const val USER_FULLNAME = stringPreferencesKey("user_fullname")
    const val USERNAME = stringPreferencesKey("user_name")
}