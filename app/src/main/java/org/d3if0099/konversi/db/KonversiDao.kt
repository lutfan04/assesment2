package org.d3if0099.konversi.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao

interface KonversiDao {@Insert
fun insert(konversi: KonversiEntity)
    @Query("SELECT * FROM konversi ORDER BY id DESC")
    fun getLastKonversi(): LiveData<List<KonversiEntity>>
    @Query("DELETE FROM konversi")
    fun clearData()
}