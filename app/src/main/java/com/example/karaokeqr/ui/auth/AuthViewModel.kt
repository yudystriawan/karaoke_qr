package com.example.karaokeqr.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.karaokeqr.data.model.responses.LoginResponse
import com.example.karaokeqr.data.network.core.Resource
import com.example.karaokeqr.data.repository.AuthRepository
import com.example.karaokeqr.ui.core.BaseViewModel
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: AuthRepository
) : BaseViewModel(repository) {

    private val _loginResponse: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()

    val loginResponse: LiveData<Resource<LoginResponse>>
        get() = _loginResponse

    fun login(
        email: String,
        password: String
    ) = viewModelScope.launch {
        _loginResponse.value = Resource.Loading
        _loginResponse.value = repository.login(email, password)
    }

    suspend fun saveAuthToken(token: String) = viewModelScope.launch {
        repository.saveAuthToken(token)
    }
}