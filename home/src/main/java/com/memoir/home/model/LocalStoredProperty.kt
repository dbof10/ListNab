package com.memoir.home.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "property")
data class LocalStoredProperty(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "is_favorite") val isFavorite: Boolean
)