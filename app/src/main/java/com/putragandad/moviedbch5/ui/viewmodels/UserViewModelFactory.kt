package com.putragandad.moviedbch5.ui.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.putragandad.moviedbch5.helper.PrefDataStoreManager

class UserViewModelFactory(val preferences: PrefDataStoreManager) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: UserViewModelFactory? = null

        fun getInstance(context: Context): UserViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: UserViewModelFactory(
                    PrefDataStoreManager(context)
                )
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(preferences) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}