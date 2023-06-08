package com.example.kost.ui.home

import androidx.recyclerview.widget.DiffUtil
import com.example.kost.data.NoteEntity

class DiffUtilCallback(
    private val mOldNoteList: List<NoteEntity>,
    private val mNewNoteList: List<NoteEntity>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldNoteList.size
    }

    override fun getNewListSize(): Int {
        return mNewNoteList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldNoteList[oldItemPosition].id == mNewNoteList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldNoteList = mOldNoteList[oldItemPosition]
        val newNoteList = mNewNoteList[newItemPosition]
        return oldNoteList.nama == newNoteList.nama && oldNoteList.alamat == newNoteList.alamat
    }
}