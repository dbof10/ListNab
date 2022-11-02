package com.memoir.home.ui

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.bumptech.glide.Glide
import com.ctech.memoir.utils.OnItemClick
import com.memoir.home.R
import com.memoir.home.databinding.ItemPropertyBinding
import com.memoir.home.viewmodel.PropertyItemViewModel


class PropertyItemViewHolder(val binding: ItemPropertyBinding) :
    RecyclerView.ViewHolder(binding.root),
    View.OnClickListener {

    var listener: OnItemClick? = null
    private val adapter: GalleryAdapter by lazy {
        GalleryAdapter(GalleryAdapter.DIFFER)
    }
    private lateinit var viewModel: PropertyItemViewModel

    companion object {
        fun create(parent: ViewGroup): PropertyItemViewHolder {
            val binding = ItemPropertyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return PropertyItemViewHolder(binding)
        }
    }

    init {

        binding.rvPhotos.layoutManager =
            LinearLayoutManager(binding.rvPhotos.context, LinearLayoutManager.HORIZONTAL, false)

        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.rvPhotos)
        binding.rvPhotos.adapter = adapter

        binding.ivFav.setOnClickListener {
            listener?.invoke(it, viewModel)
        }
    }

    fun bind(viewModel: PropertyItemViewModel) {
        this.viewModel = viewModel
        with(viewModel) {
            adapter.submitList(viewModel.imageUrls)
            binding.tvPrice.text = price
            binding.tvTitle.text = title
            binding.tvLocation.text = location

            if (isFavorite == true) {
                binding.ivFav.setImageResource(R.drawable.ic_favorite_full)
            } else {
                binding.ivFav.setImageResource(R.drawable.ic_favorite_empty)
            }
        }
    }

    override fun onClick(view: View) {
        listener?.invoke(view, viewModel)
    }
}