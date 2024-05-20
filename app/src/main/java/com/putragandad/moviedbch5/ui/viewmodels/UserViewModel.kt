package com.putragandad.moviedbch5.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.putragandad.moviedbch5.helper.PrefDataStoreManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class UserViewModel(private val preferences: PrefDataStoreManager): ViewModel() {
    fun saveLoginStatus(status: Boolean) = viewModelScope.launch(Dispatchers.IO) {
        preferences.saveLoginStatus(status)
    }

    fun readLoginStatus() : Boolean {
        return runBlocking {
            preferences.readLoginStatus.first()
        }
    }

    fun getUserEmail() = preferences.readAccountEmail.asLiveData()
    fun getUserUsername() = preferences.readAccountUsername.asLiveData()
    fun getUserFullname() = preferences.readAccountUserFullName.asLiveData()

    fun registerAccount(fullname: String, email: String, password: String, passwordCv: String) : Boolean {
        return if(password == passwordCv) {
            viewModelScope.launch(Dispatchers.IO) {
                preferences.registerUser(fullname, email, password)
            }
            true
        } else {
            false
        }
    }

    fun login(email: String, password: String) : LiveData<Boolean> {
        return liveData(Dispatchers.IO) {
            val registeredEmail = preferences.readAccountEmail.firstOrNull()
            val registeredPassword = preferences.readAccountPassword.firstOrNull()
            val isLoggedIn = (email == registeredEmail && password == registeredPassword)
            if (isLoggedIn) {
                preferences.saveLoginStatus(true)
            }
            emit(isLoggedIn)
        }
    }

    fun logout() = saveLoginStatus(false)

    fun saveAccountDetail(username: String, fullname: String, email: String) = viewModelScope.launch(Dispatchers.IO) {
        preferences.saveAccountDetail(username, fullname, email)
    }
}