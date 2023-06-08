package com.example.kost.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.kost.data.NoteEntity
import com.example.kost.databinding.CardListBinding
import com.example.kost.ui.update.UpdateActivity

class Adapter : RecyclerView.Adapter<Adapter.NoteViewHolder>() {
    private val listNotes = ArrayList<NoteEntity>()
    fun setListNotes(listNotes: List<NoteEntity>) {
        val diffCallback = DiffUtilCallback(this.listNotes, listNotes)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listNotes.clear()
        this.listNotes.addAll(listNotes)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = CardListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(listNotes[position])
    }

    override fun getItemCount(): Int {
        return listNotes.size
    }

    inner class NoteViewHolder(private val binding: CardListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(note: NoteEntity) {
            with(binding) {
                kostNama.text = note.nama
                kostAlamat.text = note.alamat
                cvItemNote.setOnClickListener {
                    val intent = Intent(it.context, UpdateActivity::class.java)
                    intent.putExtra(UpdateActivity.EXTRA_NOTE, note)
                    it.context.startActivity(intent)
                }
            }
        }
    }
}