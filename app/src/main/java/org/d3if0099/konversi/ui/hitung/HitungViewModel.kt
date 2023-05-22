package org.d3if0099.konversi.ui.hitung

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if0099.konversi.db.KonversiDao
import org.d3if0099.konversi.db.KonversiEntity
import org.d3if0099.konversi.model.HasilKonversi
import org.d3if0099.konversi.model.hitungKonversi

class HitungViewModel (private val db: KonversiDao) : ViewModel() {

    private val hasilKonversi = MutableLiveData<HasilKonversi?>()

    fun hitungKonversi(celcius: Int) {
        val dataKonversi = KonversiEntity(
            celcius = celcius
        )
        hasilKonversi.value = dataKonversi.hitungKonversi()

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                db.insert(dataKonversi)
            }
        }
    }

    fun getHasilKonversi(): LiveData<HasilKonversi?> = hasilKonversi
}