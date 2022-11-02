package com.memoir.home.repo

import androidx.room.Database
import androidx.room.RoomDatabase
import com.memoir.home.model.LocalStoredProperty

@Database(entities = [LocalStoredProperty::class], version = 1)
abstract class PropertyDatabase : RoomDatabase() {
    abstract fun propertiesDao(): PropertiesDao
}