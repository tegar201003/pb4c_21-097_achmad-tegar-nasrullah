package com.example.kost.data

import android.app.Application
import androidx.lifecycle.LiveData
import java.util.concurrent.Executors

class MyRepository(application: Application) {
    private val mNotesDao: NoteDao
    private val dbService = Executors.newSingleThreadExecutor()

    init {
        val database = NoteDatabase.getDatabase(application)
        mNotesDao = database.noteDao()
    }

    fun getAllNotes(): LiveData<List<NoteEntity>> = mNotesDao.getAllNotes()

    fun insertNote(note: NoteEntity) {
        dbService.execute { mNotesDao.insertNote(note) }
    }

    fun deleteNote(note: NoteEntity) {
        dbService.execute { mNotesDao.deleteNote(note) }
    }

    fun updateNote(note: NoteEntity) {
        dbService.execute { mNotesDao.updateNote(note) }
    }
}