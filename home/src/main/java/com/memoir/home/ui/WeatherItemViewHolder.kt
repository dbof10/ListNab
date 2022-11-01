package com.memoir.home.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ctech.memoir.utils.OnItemClick
import com.memoir.home.R
import com.memoir.home.viewmodel.WeatherItemViewModel


class WeatherItemViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {


    private val tvDate = view.findViewById<TextView>(R.id.tvDate)
    private val tvTemp = view.findViewById<TextView>(R.id.tvTemp)
    private val tvHumidity = view.findViewById<TextView>(R.id.tvHumidity)
    private val tvPressure = view.findViewById<TextView>(R.id.tvPressure)
    private val tvWeather = view.findViewById<TextView>(R.id.tvWeather)
    private val tvDesc = view.findViewById<TextView>(R.id.tvDesc)

    var listener: OnItemClick? = null

    private lateinit var viewModel: WeatherItemViewModel

    companion object {
        fun create(parent: ViewGroup): WeatherItemViewHolder {

            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_weather, parent, false)
            return WeatherItemViewHolder(view)
        }
    }

    fun bind(viewModel: WeatherItemViewModel) {
        this.viewModel = viewModel
        with(viewModel) {

            tvDate.text = date
            tvTemp.text = temp
            tvHumidity.text = humidity.toString()
            tvPressure.text = pressure.toString()
            tvWeather.text = weather
            tvDesc.text = desc

        }
    }

    override fun onClick(view: View) {
        listener?.invoke(view, viewModel)
    }
}