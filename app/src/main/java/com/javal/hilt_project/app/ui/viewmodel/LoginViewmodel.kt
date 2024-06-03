package com.javal.hilt_project.app.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewmodel @Inject constructor() : ViewModel() {
    private var _user = MutableLiveData<String>()
    val user: LiveData<String> = _user

    private var _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    fun setPassword(password: String) {
        _password.value = password
    }

    fun setUser(user: String) {
        _user.value = user
    }

    fun setUsername(pass: String) {
        _password.value= pass

    }
}