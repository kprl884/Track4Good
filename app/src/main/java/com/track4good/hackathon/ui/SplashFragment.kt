package com.track4good.hackathon.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.track4good.hackathon.MainActivity
import com.track4good.hackathon.R
import com.track4good.hackathon.common.Utils


class SplashFragment : Fragment() {
    private val auth by lazy { FirebaseAuth.getInstance() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).hideNavigationBar()
        auth.signOut()
        Utils.after(3000) { checkUser()
             }
        }

        private fun checkUser() {
            if (auth.currentUser != null) {
                findNavController().navigate(R.id.action_splashFragment_to_discoveryFragment)
            } else {
                findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
            }
        }
    }