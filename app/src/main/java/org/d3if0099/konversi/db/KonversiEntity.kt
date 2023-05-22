package org.d3if0099.konversi.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "konversi")
data class KonversiEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var tanggal: Long = System.currentTimeMillis(),
    var celcius: Int,
    var fahrenheit: Int,

)
