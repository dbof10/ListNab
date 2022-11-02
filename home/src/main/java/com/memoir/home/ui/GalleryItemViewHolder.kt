package com.memoir.home.ui

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.memoir.home.R
import com.memoir.home.databinding.ItemGalleryBinding

class GalleryItemViewHolder(private val binding: ItemGalleryBinding) : RecyclerView.ViewHolder(binding.root) {


    companion object {
        fun create(parent: ViewGroup): GalleryItemViewHolder {
            val binding = ItemGalleryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return GalleryItemViewHolder(binding)
        }
    }


    fun bind(url: String) {
        Glide.with(binding.root.context)
            .load(Uri.parse(url))
            .placeholder(R.drawable.ic_baseline_bungalow)
            .error(R.drawable.ic_baseline_bungalow)
            .into(binding.ivPhoto)
    }
}