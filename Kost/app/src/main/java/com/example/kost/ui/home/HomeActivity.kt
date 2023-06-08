package com.example.kost.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kost.R
import com.example.kost.databinding.ActivityHomeBinding
import com.example.kost.ui.ViewModelFactory
import com.example.kost.ui.update.UpdateActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val mainViewModel = getViewModel(this@HomeActivity)
        mainViewModel.getAllNotes().observe(this) { noteList ->
            if (noteList != null) {
                adapter.setListNotes(noteList)
            }
        }

        adapter = Adapter()

        binding.rvNotes.layoutManager = LinearLayoutManager(this)
        binding.rvNotes.setHasFixedSize(true)
        binding.rvNotes.adapter = adapter

        binding.fabAdd.setOnClickListener { view ->
            if (view.id == R.id.fab_add) {
                val intent = Intent(this@HomeActivity, UpdateActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun getViewModel(activity: AppCompatActivity): HomeViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[HomeViewModel::class.java]
    }
}