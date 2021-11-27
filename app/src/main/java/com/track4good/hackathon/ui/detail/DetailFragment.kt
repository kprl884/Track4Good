package com.track4good.hackathon.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.track4good.hackathon.MainActivity
import com.track4good.hackathon.R
import com.track4good.hackathon.common.BaseFragment
import com.track4good.hackathon.databinding.FragmentDetailBinding
import com.track4good.hackathon.domain.entity.Advert
import com.track4good.hackathon.domain.entity.ResultData
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject


@AndroidEntryPoint
class DetailFragment @Inject constructor(
) : BaseFragment<DetailViewModel, FragmentDetailBinding>() {
    override val layoutRes = R.layout.fragment_detail
    override val viewModel: DetailViewModel by viewModels()

    override fun observeViewModel() {
        viewModel.detailAdvertData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is ResultData.Success -> {
                    it.data?.let { it1 -> updateUi(it1) }
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
        (activity as MainActivity).showNavigationBar()
        val id = arguments?.get("detailAdvertId") as String
        viewModel.getAdvert(id)
        clickListeners()
    }


    private fun clickListeners() {

    }


    private fun updateUi(advert: Advert) {
        binding.detailItemTitle.text = advert.title
    }
}