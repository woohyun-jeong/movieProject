package com.whjung.movieproject.view.base

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj : T)

    @Delete
    fun delete(obj : T)

    @Update(onConflict = OnConflictStrategy.ABORT)
    fun update(obj : T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg obj: T)

    @Delete
    fun deleteAll(vararg obj : T)

    @Update
    fun updateAll(vararg obj: T)
}