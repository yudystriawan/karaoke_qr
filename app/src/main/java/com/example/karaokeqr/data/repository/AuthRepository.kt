package com.example.karaokeqr.data.repository

import com.example.karaokeqr.data.UserPreferences
import com.example.karaokeqr.data.network.AuthApi
import com.example.karaokeqr.data.repository.core.BaseRepository

class AuthRepository(
    private val api: AuthApi,
    private val preferences: UserPreferences
) : BaseRepository() {

    suspend fun login(
        email: String,
        password: String
    ) = safeApiCall {
        api.login(email, password)
    }


    suspend fun saveAuthToken(token: String) {
        preferences.saveAuthToken(token)
    }


}