package com.memoir.home.repo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.memoir.home.model.LocalStoredProperty

@Dao
interface PropertiesDao {
    @Query("SELECT * FROM property")
    fun getAll(): List<LocalStoredProperty>


    @Query("SELECT * FROM property WHERE id = :id")
    fun getBy(id: String): LocalStoredProperty?

    @Insert
    fun insert(entity: LocalStoredProperty)

    @Query("UPDATE property SET is_favorite = :fav WHERE id = :id")
    fun favorite(id: String, fav: Boolean)

}