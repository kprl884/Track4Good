package com.track4good.hackathon.ui.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.track4good.hackathon.R
import com.track4good.hackathon.common.BaseFragment
import com.track4good.hackathon.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment @Inject constructor(
) : BaseFragment<ProfileViewModel, FragmentProfileBinding>() {
    override val layoutRes = R.layout.fragment_profile
    override val viewModel: ProfileViewModel by viewModels()
    private val auth by lazy { FirebaseAuth.getInstance() }


    @InternalCoroutinesApi
    override fun viewCreated(view: View, savedInstanceState: Bundle?) {
        updateUI()
        clickListeners()
    }

    private fun clickListeners() {
        binding.signOut.setOnClickListener {
            auth.signOut()
            navigateToLoginFragment()
        }
    }

    private fun updateUI() {
        if (auth.currentUser?.displayName != null) {
            binding.etName.setText(auth.currentUser?.displayName)
        }
        if (auth.currentUser?.phoneNumber != null) {
            binding.etPhone.setText(auth.currentUser?.phoneNumber)
        }
        binding.etGmail.setText(auth.currentUser?.email)
    }

    private fun navigateToLoginFragment() {
        findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
    }

    override fun observeViewModel() {

    }
}