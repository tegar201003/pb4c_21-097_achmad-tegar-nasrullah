package com.example.kost.ui.update

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.kost.R
import com.example.kost.data.NoteEntity
import com.example.kost.databinding.ActivityUpdateBinding
import com.example.kost.ui.ViewModelFactory

class UpdateActivity : AppCompatActivity() {
    private var isEdit = false
    private var noteEntity: NoteEntity? = null
    private var oldNote: NoteEntity? = null
    private lateinit var viewModel: UpdateViewModel

    private lateinit var binding: ActivityUpdateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = getViewModel(this@UpdateActivity)

        noteEntity = intent.getParcelableExtra(EXTRA_NOTE)
        oldNote = noteEntity
        if (noteEntity != null) {
            isEdit = true
        } else {
            noteEntity = NoteEntity()
        }

        val actionBarTitle: String
        val btnTitle: String

        if (isEdit) {
            actionBarTitle = getString(R.string.change)
            btnTitle = getString(R.string.update)
            if (noteEntity != null) {
                noteEntity?.let { note ->
                    binding.edtTitle.setText(note.nama)
                    binding.edtDescription.setText(note.alamat)
                }
                binding.btnDelete.setOnClickListener {
                    showAlertDialog(ALERT_DIALOG_DELETE)
                }
            }
        } else {
            actionBarTitle = getString(R.string.add)
            btnTitle = getString(R.string.save)
            binding.btnDelete.visibility = View.GONE
        }

        supportActionBar?.title = actionBarTitle
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnSubmit.text = btnTitle
        binding.btnSubmit.setOnClickListener {
            val title = binding.edtTitle.text.toString().trim()
            val description = binding.edtDescription.text.toString().trim()

            when {
                title.isEmpty() -> {
                    binding.edtTitle.error = getString(R.string.empty)
                }
                description.isEmpty() -> {
                    binding.edtDescription.error = getString(R.string.empty)
                }
                else -> {
                    noteEntity.let { note ->
                        note?.nama = title
                        note?.alamat = description
                    }
                    if (isEdit) {
                        viewModel.updateNote(noteEntity as NoteEntity)
                        showToast(getString(R.string.changed))
                    } else {
                        noteEntity.let { note ->

                        }
                        viewModel.insertNote(noteEntity as NoteEntity)
                        showToast(getString(R.string.added))
                    }

                    finish()
                }
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                if (noteEntity != null) {
                    if (oldNote != noteEntity) {
                        showAlertDialog(ALERT_DIALOG_CLOSE)
                    } else {
                        finish()
                    }
                } else if (binding.edtTitle.text.isEmpty() && binding.edtDescription.text.isEmpty()) {
                    finish()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (noteEntity != null) {
            if (oldNote != noteEntity) {
                showAlertDialog(ALERT_DIALOG_CLOSE)
            } else {
                finish()
            }
        } else {
            finish()
        }
    }

    private fun showAlertDialog(type: Int) {
        val isDialogClose = type == ALERT_DIALOG_CLOSE
        val dialogTitle: String
        val dialogMessage: String
        if (isDialogClose) {
            dialogTitle = getString(R.string.cancel)
            dialogMessage = getString(R.string.message_cancel)
        } else {
            dialogMessage = getString(R.string.message_delete)
            dialogTitle = getString(R.string.delete)
        }
        val alertDialogBuilder = AlertDialog.Builder(this)
        with(alertDialogBuilder) {
            setTitle(dialogTitle)
            setMessage(dialogMessage)
            setCancelable(false)
            setPositiveButton(getString(R.string.yes)) { _, _ ->
                if (!isDialogClose) {
                    viewModel.deleteNote(noteEntity as NoteEntity)
                    showToast(getString(R.string.deleted))
                }
                finish()
            }
            setNegativeButton(getString(R.string.no)) { dialog, _ -> dialog.cancel() }
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    private fun getViewModel(activity: AppCompatActivity): UpdateViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[UpdateViewModel::class.java]
    }

    companion object {
        const val EXTRA_NOTE = "extra_note"
        const val ALERT_DIALOG_CLOSE = 1
        const val ALERT_DIALOG_DELETE = 2
    }
}