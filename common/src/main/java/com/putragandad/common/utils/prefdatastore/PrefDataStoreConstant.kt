package com.putragandad.common.utils.prefdatastore

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PrefDataStoreConstant {
    const val APP_DATASTORE = "synflix_datastore"
    val ISLOGIN = booleanPreferencesKey("is_login")
    val USER_EMAIL = stringPreferencesKey("user_email")
    val USER_PASSWORD = stringPreferencesKey("user_password")
    val USER_FULLNAME = stringPreferencesKey("user_fullname")
    val USERNAME = stringPreferencesKey("user_name")
    val PROFILE_URI = stringPreferencesKey("profile_picture_uri")
}