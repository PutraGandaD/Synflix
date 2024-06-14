package com.putragandad.moviedbch5.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.putragandad.moviedbch5.domain.models.users.AccountDetail
import com.putragandad.moviedbch5.domain.usecases.users.CheckLoginUseCase
import com.putragandad.moviedbch5.domain.usecases.users.ReadUserInfoUseCase
import com.putragandad.moviedbch5.domain.usecases.users.SetProfilePictureUseCase
import com.putragandad.moviedbch5.domain.usecases.users.UpdateUserInfoUseCase
import com.putragandad.moviedbch5.domain.usecases.users.UserLoginUseCase
import com.putragandad.moviedbch5.domain.usecases.users.UserLogoutUseCase
import com.putragandad.moviedbch5.domain.usecases.users.UserRegisterUseCase
import kotlinx.coroutines.launch

class UserViewModel(
    private val checkLoginUseCase: CheckLoginUseCase,
    private val userLoginUseCase : UserLoginUseCase,
    private val userRegisterUseCase: UserRegisterUseCase,
    private val readUserInfoUseCase: ReadUserInfoUseCase,
    private val userLogoutUseCase: UserLogoutUseCase,
    private val updateUserInfoUseCase: UpdateUserInfoUseCase,
    private val setProfilePictureUseCase: SetProfilePictureUseCase
): ViewModel() {
    private val _loginStatus = MutableLiveData<Boolean>()
    val loginStatus: LiveData<Boolean>
        get() = _loginStatus

    private val _userInfo = MutableLiveData<AccountDetail>()
    val userInfo: LiveData<AccountDetail> = _userInfo

    init {
        readLoginStatus()
        readAccountDetail()
    }

    private fun readLoginStatus() {
        viewModelScope.launch {
            checkLoginUseCase.invoke().collect {
                _loginStatus.value = it
            }
        }
    }

    private fun readAccountDetail() {
        viewModelScope.launch {
            readUserInfoUseCase.invoke().collect {
                _userInfo.value = it
            }
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            userLoginUseCase.invoke(email, password)
            readLoginStatus()
        }
    }

    fun register(fullname: String, email: String, password: String) {
        viewModelScope.launch {
            userRegisterUseCase.invoke(fullname, email, password)
        }
    }

    fun logout() {
        viewModelScope.launch {
            userLogoutUseCase.invoke()
        }
    }

    fun saveAccountDetail(username: String, fullname: String, email: String) {
        viewModelScope.launch {
            updateUserInfoUseCase.invoke(username, fullname, email)
        }
    }

    fun setProfilePicture(uri: String) {
        viewModelScope.launch {
            setProfilePictureUseCase.invoke(uri)
        }
    }
}