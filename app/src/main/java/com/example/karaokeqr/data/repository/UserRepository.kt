package com.example.karaokeqr.data.repository

import com.example.karaokeqr.data.network.UserApi
import com.example.karaokeqr.data.repository.core.BaseRepository

class UserRepository(
    private val api: UserApi
) : BaseRepository() {

    suspend fun getUser() = safeApiCall {
        api.getUser()
    }

}