package com.track4good.hackathon.ui.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.track4good.hackathon.R
import com.track4good.hackathon.common.BaseFragment
import com.track4good.hackathon.databinding.FragmentProfileBinding
import com.track4good.hackathon.domain.entity.ResultData
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment @Inject constructor(
) : BaseFragment<ProfileViewModel, FragmentProfileBinding>() {
    override val layoutRes = R.layout.fragment_profile
    override val viewModel: ProfileViewModel by viewModels()
    private val auth by lazy { FirebaseAuth.getInstance() }

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
        binding.btnSignOut.setOnClickListener {
            auth.signOut()
            navigateToLoginFragment()
        }
    }

    private fun navigateToLoginFragment() {
        findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
    }
}