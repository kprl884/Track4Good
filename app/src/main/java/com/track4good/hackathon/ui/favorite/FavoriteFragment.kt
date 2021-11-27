package com.track4good.hackathon.ui.favorite


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
import com.track4good.hackathon.databinding.FragmentFavoriteBinding
import com.track4good.hackathon.domain.entity.Advert
import com.track4good.hackathon.domain.entity.ResultData
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject


@AndroidEntryPoint
class FavoriteFragment @Inject constructor(
) : BaseFragment<FavoriteViewModel, FragmentFavoriteBinding>() {
    override val layoutRes = R.layout.fragment_favorite
    override val viewModel: FavoriteViewModel by viewModels()
    private lateinit var favoriteAdapter: FavoriteAdapter

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

    private fun updateAdapterList(advertList: ArrayList<Advert>) {
        favoriteAdapter.searchAdvertListData = advertList
        favoriteAdapter.notifyDataSetChanged()
    }

    @InternalCoroutinesApi
    override fun viewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getAdvert()
        favoriteAdapter = FavoriteAdapter()
        setAdapter()
        (activity as MainActivity).showNavigationBar()
        clickListeners()
    }

    private fun setAdapter() {
        favoriteAdapter = FavoriteAdapter()
        binding.favRv.adapter = favoriteAdapter
        binding.favRv.layoutManager = LinearLayoutManager(requireContext())
        favoriteAdapter.setOnItemClickListener {
            navigateToDetailFragment(it)
        }
    }

    private fun clickListeners() {

    }

    private fun navigateToDetailFragment(id: String) {
        val detailIdBundle =
            bundleOf("detailAdvertId" to id)
        findNavController().navigate(
            R.id.action_favoriteFragment_to_detailFragment, detailIdBundle
        )
    }

}