package com.track4good.hackathon.ui.discovery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.track4good.hackathon.R
import com.track4good.hackathon.databinding.AdvertCardviewItemBinding
import com.track4good.hackathon.domain.entity.Advert
import javax.inject.Inject

class DiscoveryAdapter @Inject constructor(
) : RecyclerView.Adapter<DiscoveryAdapter.ViewHolder>() {

    private var onItemClickListener: ((id: String) -> Unit)? = null

    fun setOnItemClickListener(listener: (id: String) -> Unit) {
        onItemClickListener = listener
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = AdvertCardviewItemBinding.bind(itemView)

        //val userName = binding.userName
        //val userUsername = binding.userUsername
        val advertDescription = binding.itemDescription
        val advertImage = binding.itemIv
    }

    private val diffUtil = object : DiffUtil.ItemCallback<Advert>() {
        override fun areItemsTheSame(
            oldItem: Advert,
            newItem: Advert
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: Advert,
            newItem: Advert
        ): Boolean {
            return oldItem.equals(newItem)
        }
    }

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    var searchAdvertListData: List<Advert>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.advert_cardview_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.apply {
            holder.advertDescription.text = searchAdvertListData[position].description.toString()
            holder.advertImage.setImageResource(R.drawable.profile_button)
        }.setOnClickListener {
            onItemClickListener?.let {
                it(searchAdvertListData[position].id)
            }
        }
    }

    override fun getItemCount(): Int {
        return searchAdvertListData.size
    }
}