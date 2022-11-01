package com.memoir.home.viewmodel

import androidx.lifecycle.ViewModel
import com.ctech.memoir.core.arch.*
import com.memoir.home.model.Weather
import com.memoir.home.repo.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.ReceiveChannel
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repo: HomeRepository,
    private val scopeProvider: CoroutineScopeProvider
) : ViewModel() {


    companion object {
       internal const val LIMIT_DATE = 7
    }

    val store = ViewStateStore(HomeViewState(), scopeProvider)


    fun fetch(city: String) {
        store.dispatchActions(fetchItems(city))
    }

    private fun fetchItems(city: String): ReceiveChannel<Action<HomeViewState>> {

        return produceActions(scopeProvider.async) {
            emit {
                copy(loading = true)
            }

            try {
                val forecast = repo.fetch(city, LIMIT_DATE)
                val dateFormatter = SimpleDateFormat("dd/mm/yyyy")
                val vms = forecast.list.mapIndexed { index, item ->
                    val date = dateFormatter.format(Date(item.dt))
                    val displayTemp = String.format("%.2f °C", (item.temp.max + item.temp.min) / 2)
                    val weather = item.weather.firstOrNull() ?: Weather("","")
                    WeatherItemViewModel(
                        index, date, displayTemp, item.pressure, item.humidity,
                        weather.main, weather.description
                    )
                }
                emit {
                    HomeViewState(vms, false, null)
                }
            } catch (e: Exception) {
                emit {
                    HomeViewState(null, false, e)
                }
            }
        }


    }

}