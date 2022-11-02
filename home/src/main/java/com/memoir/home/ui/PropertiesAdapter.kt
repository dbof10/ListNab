package com.memoir.home.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ctech.memoir.utils.OnItemClick
import com.memoir.home.viewmodel.PropertyItemViewModel

class PropertiesAdapter(diffResult: DiffUtil.ItemCallback<PropertyItemViewModel>): ListAdapter<PropertyItemViewModel, PropertyItemViewHolder>(diffResult) {
    private val pool = RecyclerView.RecycledViewPool()

    var listener: OnItemClick? = null

    companion object {

        val DIFFER = object : DiffUtil.ItemCallback<PropertyItemViewModel>() {
            override fun areItemsTheSame(oldItem: PropertyItemViewModel, newItem: PropertyItemViewModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: PropertyItemViewModel, newItem: PropertyItemViewModel): Boolean {
                return oldItem == newItem
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertyItemViewHolder {
        return PropertyItemViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: PropertyItemViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.binding.rvPhotos.setRecycledViewPool(pool)
        holder.listener = listener
    }
}