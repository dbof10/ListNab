package com.memoir.home.viewmodel

import androidx.lifecycle.ViewModel
import com.ctech.memoir.core.arch.Action
import com.ctech.memoir.core.arch.CoroutineScopeProvider
import com.ctech.memoir.core.arch.ViewStateStore
import com.ctech.memoir.core.arch.emit
import com.ctech.memoir.core.arch.produceActions
import com.memoir.home.model.Property
import com.memoir.home.repo.HomeRepository
import com.memoir.home.util.AddressUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.NumberFormat
import java.util.Currency
import javax.inject.Inject
import kotlinx.coroutines.channels.ReceiveChannel


@HiltViewModel
class PropertiesViewModel @Inject constructor(
    private val repo: HomeRepository,
    private val scopeProvider: CoroutineScopeProvider
) : ViewModel() {


    val store = ViewStateStore(HomeViewState(), scopeProvider)

    fun fetch() {
        store.dispatchActions(fetchItems())
    }

    private fun fetchItems(): ReceiveChannel<Action<HomeViewState>> {

        return produceActions(scopeProvider.async) {
            emit {
                copy(loading = true)
            }

            try {
                val properties = repo.fetch()

                val vms = properties.map {
                    toViewModel(it)
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

    private fun toViewModel(property: Property): PropertyItemViewModel {
        val address = property.listing.address
        val displayAddress = AddressUtil.format(address)
        val price = property.listing.prices
        val format: NumberFormat = NumberFormat.getCurrencyInstance()
        format.maximumFractionDigits = 0
        format.currency = Currency.getInstance(price.currency)
        val displayPrice = format.format(price.buy.price)
        val de = property.listing.localization.de
        val imageUrls = de.attachments.map { it.url }
        val title = de.text.title
        return PropertyItemViewModel(
            property.id, property.isFavorite, displayAddress, displayPrice,
            imageUrls, title
        )
    }

    fun onFavClickedAt(item: PropertyItemViewModel) {
        store.dispatchAction {
            return@dispatchAction Action {

                val isFav = item.isFavorite ?: false
                val toggleFav = isFav.not()
                repo.favorite(item.id, toggleFav)
                val newContent = store.state.content!!.map {
                    if (it.id == item.id) {
                        return@map it.copy(isFavorite = toggleFav)
                    } else {
                        return@map it
                    }
                }
                copy(content = newContent)
            }
        }

    }

}