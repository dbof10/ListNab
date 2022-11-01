package com.memoir.home.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.ctech.memoir.utils.OnItemClick
import com.memoir.home.viewmodel.WeatherItemViewModel

class WeatherAdapter(diffResult: DiffUtil.ItemCallback<WeatherItemViewModel>): ListAdapter<WeatherItemViewModel, WeatherItemViewHolder>(diffResult) {


    var listener: OnItemClick? = null
    companion object {

        val DIFFER = object : DiffUtil.ItemCallback<WeatherItemViewModel>() {
            override fun areItemsTheSame(oldItem: WeatherItemViewModel, newItem: WeatherItemViewModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: WeatherItemViewModel, newItem: WeatherItemViewModel): Boolean {
                return oldItem == newItem
            }

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherItemViewHolder {
        return WeatherItemViewHolder.create(parent)

    }

    override fun onBindViewHolder(holder: WeatherItemViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.listener = listener
    }
}