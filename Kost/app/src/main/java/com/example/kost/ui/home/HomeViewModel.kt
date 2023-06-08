package com.example.kost.ui.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.kost.data.MyRepository
import com.example.kost.data.NoteEntity

class HomeViewModel(app: Application) : ViewModel() {
    private val mNoteRepository = MyRepository(app)
    fun getAllNotes(): LiveData<List<NoteEntity>> = mNoteRepository.getAllNotes()
}