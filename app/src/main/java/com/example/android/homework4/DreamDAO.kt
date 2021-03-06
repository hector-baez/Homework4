package com.example.android.homework4

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface DreamDAO {

    //Get all dreams; sort the results by id
    @Query("SELECT * FROM dream_table ORDER BY id ASC")
    fun getAllDreams() : Flow<List<Dream>>

    //Insert a dream; if there is conflict, we ignore the insert
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(dream:Dream)

    //Update a dream with a given id and a set of fields
    @Query("UPDATE dream_table SET title=:title, content=:content, reflection=:reflection, emotion=:emotion WHERE id=:id")
    suspend fun update(title:String, content:String, reflection:String, emotion:String, id:Int)

    //Delete a dream with a given id
    @Query("DELETE FROM dream_table WHERE id=:id")
    suspend fun delete(id:Int)

    //Get a dream with a given id
    @Query ("SELECT * FROM dream_table WHERE id=:id")
    fun getDream(id:Int) : Flow<Dream>

}