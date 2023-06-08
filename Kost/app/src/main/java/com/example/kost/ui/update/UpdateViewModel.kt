package com.example.kost.ui.update

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.kost.data.MyRepository
import com.example.kost.data.NoteEntity

class UpdateViewModel(app: Application) : ViewModel() {

    private val mNoteRepository = MyRepository(app)

    fun insertNote(note: NoteEntity) {
        mNoteRepository.insertNote(note)
    }

    fun updateNote(note: NoteEntity) {
        mNoteRepository.updateNote(note)
    }

    fun deleteNote(note: NoteEntity) {
        mNoteRepository.deleteNote(note)
    }

}