package com.memoir.home.repo

import com.memoir.home.model.LocalStoredProperty
import com.memoir.home.model.Property


open class HomeRepository(
    private val apiClient: PropertyApiClient,
    private val db: PropertiesDao
) {


    open suspend fun fetch(): List<Property> {
        val remoteProperties = apiClient.fetch().results
        val favorites = db.getAll()
        val favoritePropertyTable = favorites.associate {
            it.id to it.isFavorite
        }

        return remoteProperties.map {
            if (favoritePropertyTable.contains(it.id)) {
                val isFav = favoritePropertyTable[it.id]
                return@map it.copy(isFavorite = isFav)
            }
            return@map it
        }
    }

    open fun favorite(id: String, fav: Boolean) {
        val localStored = db.getBy(id)
        if (localStored == null) {
            db.insert(LocalStoredProperty(id, fav))
        } else {
            db.favorite(id, fav)
        }
    }

}