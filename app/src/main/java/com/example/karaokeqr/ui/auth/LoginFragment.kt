package com.example.karaokeqr.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.karaokeqr.data.network.AuthApi
import com.example.karaokeqr.data.network.core.Resource
import com.example.karaokeqr.data.repository.AuthRepository
import com.example.karaokeqr.databinding.FragmentLoginBinding
import com.example.karaokeqr.ui.core.BaseFragment
import com.example.karaokeqr.ui.enable
import com.example.karaokeqr.ui.handleApiError
import com.example.karaokeqr.ui.home.HomeActivity
import com.example.karaokeqr.ui.startNewActivity
import com.example.karaokeqr.ui.visible
import kotlinx.coroutines.launch

class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.progressCard.visible(false)
        binding.btnLogin.enable(false)

        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {

            binding.progressCard.visible(it is Resource.Loading)

            when (it) {
                is Resource.Success -> {
                    lifecycleScope.launch {
                        viewModel.saveAuthToken(it.values.user.token)
                    }
                    requireActivity().startNewActivity(HomeActivity::class.java)
                }
                is Resource.Failure -> handleApiError(it) { login() }
            }
        })

        binding.passwordInput.addTextChangedListener {
            val emailStr = binding.emailInput.text.toString().trim()
            binding.btnLogin.enable(
                emailStr.isNotEmpty() && it.toString().isNotEmpty() && it.toString().length >= 6
            )
        }

        binding.btnLogin.setOnClickListener {
            login()
        }
    }

    private fun login() {
        val emailStr = binding.emailInput.text.toString().trim()
        val passwordStr = binding.emailInput.text.toString().trim()
        viewModel.login(emailStr, passwordStr)
    }

    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() =
        AuthRepository(remoteDataSource.buildApi(AuthApi::class.java), userPreferences)

}