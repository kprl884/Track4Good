package com.track4good.hackathon.ui.auth

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.track4good.hackathon.R
import com.track4good.hackathon.common.BaseFragment
import com.track4good.hackathon.databinding.FragmentLoginBinding
import com.track4good.hackathon.domain.entity.ResultData
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject


@AndroidEntryPoint
class LoginFragment @Inject constructor(
) : BaseFragment<LoginViewModel, FragmentLoginBinding>() {
    override val layoutRes = R.layout.fragment_login
    override val viewModel: LoginViewModel by viewModels()
    private val email by lazy { binding.eTextEmail.text.toString() }
    private val password by lazy { binding.eTextPassword.text.toString() }
    override fun observeViewModel() {
        viewModel.firebaseUserData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is ResultData.Success -> {
                    navigateToDiscoveryFragment()
                }
                is ResultData.Failed -> {
                }
                is ResultData.Loading -> {

                }
                is ResultData.Progress -> {

                }
            }
        })
    }

    @InternalCoroutinesApi
    override fun viewCreated(view: View, savedInstanceState: Bundle?) {
        clickListeners()
    }

    @InternalCoroutinesApi
    private fun clickListeners() {
        binding.textCreateAccount.setOnClickListener {
            findNavController().navigate(
                R.id.action_loginFragment_to_registerFragment
            )
        }

        binding.btnLogin.setOnClickListener {
            if (validEmail() && validPassword()) {
                viewModel.login(
                    binding.eTextEmail.text.toString(),
                    binding.eTextPassword.text.toString()
                )
            } else {
                navigateToDiscoveryFragment()
            }
        }
    }

    private fun navigateToDiscoveryFragment() {
        findNavController().navigate(
            R.id.action_loginFragment_to_discoveryFragment
        )
    }

    private fun validEmail(): Boolean {
        if (email.isNullOrEmpty()) {
            binding.eTextEmail.requestFocus()
            binding.textInputLayoutEmail.error = "Email is required!"
            return false
        } else if (email!!.length < 5) {
            binding.eTextEmail.requestFocus()
            binding.textInputLayoutEmail.error = "Email is invalid!"
            Toast.makeText(context, "Must be longer than 5 characters", Toast.LENGTH_SHORT).show()
            return false
        } else if (email!!.length > 50) {
            binding.eTextEmail.requestFocus()
            binding.textInputLayoutEmail.error = "Email is invalid!"
            Toast.makeText(context, "Must be shorter than 50 characters", Toast.LENGTH_SHORT).show()
            return false
        } else if (!email!!.contains(Regex("@+."))) {
            binding.eTextEmail.requestFocus()
            binding.textInputLayoutEmail.error = "Email is invalid!"
            Toast.makeText(context, "Must require @ and .", Toast.LENGTH_SHORT).show()
            return false
        }


        binding.textInputLayoutEmail.isErrorEnabled = false

        return true
    }

    private fun validPassword(): Boolean {
        /* val passwordRegex = Pattern.compile(
             "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])([^a-zA-Z0-9 ])$"
         )*/
        if (password.isNullOrEmpty()) {
            binding.eTextPassword.requestFocus()
            binding.textInputLayoutPassword.error = "Password is required!"
            return false
        } else if (password!!.length < 7) {
            binding.eTextPassword.requestFocus()
            binding.textInputLayoutPassword.error = "Password is too short!"
            Toast.makeText(
                context,
                "Must be longer than 7 characters ${binding.eTextPassword}",
                Toast.LENGTH_SHORT
            ).show()
            return false
        } else if (password!!.length > 40) {
            binding.eTextPassword.requestFocus()
            binding.textInputLayoutPassword.error = "Password is too long!"
            Toast.makeText(context, "Must be shorter than 40 characters", Toast.LENGTH_SHORT).show()
            return false
        } else if (!password!!.contains(Regex("[0-9]"))) {
            binding.eTextPassword.requestFocus()
            binding.textInputLayoutPassword.error =
                "Password must contain one digit, one uppercase letter," +
                        "one lowercase letter and one special character!"
            return false
        } else if (!password!!.contains(Regex("[A-Z]"))) {
            binding.eTextPassword.requestFocus()
            binding.textInputLayoutPassword.error =
                "Password must contain one digit, one uppercase letter," +
                        "one lowercase letter and one special character!"
            return false
        } else if (!password!!.contains(Regex("[a-z]"))) {
            binding.eTextPassword.requestFocus()
            binding.textInputLayoutPassword.error =
                "Password must contain one digit, one uppercase letter," +
                        "one lowercase letter and one special character!"
            return false
        } else if (!password!!.contains(Regex("[^a-zA-Z0-9 ]"))) {
            binding.eTextPassword.requestFocus()
            binding.textInputLayoutPassword.error =
                "Password must contain one digit, one uppercase letter," +
                        "one lowercase letter and one special character!"
            return false
        }
        binding.textInputLayoutPassword.isErrorEnabled = false
        return true
    }
}