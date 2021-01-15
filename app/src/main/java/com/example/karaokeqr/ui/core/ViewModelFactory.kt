package com.example.karaokeqr.ui.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.karaokeqr.data.repository.AuthRepository
import com.example.karaokeqr.data.repository.UserRepository
import com.example.karaokeqr.data.repository.core.BaseRepository
import com.example.karaokeqr.ui.auth.AuthViewModel
import com.example.karaokeqr.ui.home.HomeViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val repository: BaseRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> AuthViewModel(repository as AuthRepository) as T
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(repository as UserRepository) as T
            else -> throw IllegalArgumentException("ViewModelClass Not Found.")
        }
    }
}