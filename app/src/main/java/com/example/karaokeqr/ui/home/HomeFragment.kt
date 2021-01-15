package com.example.karaokeqr.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.karaokeqr.data.model.User
import com.example.karaokeqr.data.network.UserApi
import com.example.karaokeqr.data.network.core.Resource
import com.example.karaokeqr.data.repository.UserRepository
import com.example.karaokeqr.databinding.FragmentHomeBinding
import com.example.karaokeqr.ui.core.BaseFragment
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding, UserRepository>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getUser()

        viewModel.user.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    updateUI(it.values.user)
                }
                is Resource.Loading -> {
                    //Todo: Show ProgressBar
                }
            }
        })

        binding.btnLogout.setOnClickListener {
            logout()
        }
    }

    private fun updateUI(user: User) {

    }

    override fun getViewModel() = HomeViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHomeBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): UserRepository {
        val token = runBlocking { userPreferences.authToken.first() }
        val api = remoteDataSource.buildApi(UserApi::class.java, token)
        return UserRepository(api)
    }

}