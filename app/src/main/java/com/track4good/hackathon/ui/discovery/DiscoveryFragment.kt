package com.track4good.hackathon.ui.discovery

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.track4good.hackathon.MainActivity
import com.track4good.hackathon.R
import com.track4good.hackathon.common.BaseFragment
import com.track4good.hackathon.databinding.FragmentDiscoveryBinding
import com.track4good.hackathon.domain.entity.Advert
import com.track4good.hackathon.domain.entity.ResultData
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject


@AndroidEntryPoint
class DiscoveryFragment @Inject constructor(
) : BaseFragment<DiscoveryViewModel, FragmentDiscoveryBinding>() {
    override val layoutRes = R.layout.fragment_discovery
    override val viewModel: DiscoveryViewModel by viewModels()
    private lateinit var discoveryAdapter: DiscoveryAdapter
    override fun observeViewModel() {
        viewModel.advertData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is ResultData.Success -> {
                    it.data?.let { it1 -> updateAdapterList(it1) }
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
        viewModel.getAdvert()
        discoveryAdapter = DiscoveryAdapter()
        setAdapter()
        (activity as MainActivity).showNavigationBar()
        clickListeners()
    }


    private fun updateAdapterList(advertList: ArrayList<Advert>) {
        discoveryAdapter.searchAdvertListData = advertList
        discoveryAdapter.notifyDataSetChanged()
    }

    private fun setAdapter() {
        discoveryAdapter = DiscoveryAdapter()
        binding.rvDiscovery.adapter = discoveryAdapter

        binding.rvDiscovery.layoutManager = LinearLayoutManager(requireContext())
        discoveryAdapter.setOnItemClickListener {
            navigateToDetailFragment(it)
        }
    }

    private fun navigateToDetailFragment(id: String) {
        val detailIdBundle =
            bundleOf("detailAdvertId" to id)
        findNavController().navigate(
            R.id.action_discoveryFragment_to_detailFragment, detailIdBundle
        )
    }

    private fun clickListeners() {

    }

    private fun navigateToLoginFragment() {
        findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
    }
}