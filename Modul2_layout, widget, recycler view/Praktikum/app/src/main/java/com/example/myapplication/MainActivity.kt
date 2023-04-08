package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerview: RecyclerView
    private val listKost = listOf<Kost>(
        Kost(nama = "Kost Abah Hafid", alamat = "Telang Indah Gg 6"),
        Kost(nama = "Kost Mawar", alamat = "Telang Indah Gg 6"),
        Kost(nama = "Kost Indah", alamat = "Telang Indah Gg 6"),
        Kost(nama = "Kost Abah Yadi", alamat = "Telang Indah Gg 6"),
        Kost(nama = "Kost Rahman", alamat = "Telang Indah Gg 6"),
        Kost(nama = "Kost Melati", alamat = "Telang Indah Gg 6"),
        Kost(nama = "Kost Putra", alamat = "Telang Indah Gg 6"),
        Kost(nama = "Kost Putri", alamat = "Telang Indah Gg 6"),
        Kost(nama = "Kost Aang", alamat = "Telang Indah Gg 6")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerview = findViewById(R.id.recyclerview)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = Adapter(listKost)
    }
}