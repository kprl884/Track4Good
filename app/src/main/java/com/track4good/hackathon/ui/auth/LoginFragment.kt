package com.track4good.hackathon.ui.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
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

    override fun observeViewModel() {
        viewModel.firebaseUserData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is ResultData.Success -> {

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

    private fun clickListeners() {

    }


}