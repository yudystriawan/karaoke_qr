package com.example.karaokeqr.ui.core

import androidx.lifecycle.ViewModel
import com.example.karaokeqr.data.network.UserApi
import com.example.karaokeqr.data.repository.core.BaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseViewModel(
    private val repository: BaseRepository
) : ViewModel() {

    suspend fun logout(api: UserApi) = withContext(Dispatchers.IO) { repository.logout(api) }

}