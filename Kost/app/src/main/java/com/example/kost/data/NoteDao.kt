package com.example.kost.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertNote(note: NoteEntity)

    @Delete
    fun deleteNote(note: NoteEntity)

    @Update
    fun updateNote(note: NoteEntity)

    @Query("SELECT * FROM noteentity ORDER BY id ASC")
    fun getAllNotes(): LiveData<List<NoteEntity>>
}