package com.example.kost.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "nama")
    var nama: String? = null,

    @ColumnInfo(name = "alamat")
    var alamat: String? = null,
) : Parcelable