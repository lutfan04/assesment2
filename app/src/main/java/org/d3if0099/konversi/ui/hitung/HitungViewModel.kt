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

class HitungViewModel (private val db: KonversiDao) : ViewModel() {

    private val hasilKonversi = MutableLiveData<HasilKonversi?>()
    val data = db.getLastKonversi()
    fun hitungKonversi(celcius: Int, fahrenheit: Int) {
        val hasil = (celcius * 9 / 5) + 32
        hasilKonversi.value = HasilKonversi(hasil)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val dataKonversi = KonversiEntity(
                    celcius = celcius,
                    fahrenheit = fahrenheit
                    )
                db.insert(dataKonversi)
            }
        }

    }

    fun getHasilKonversi(): LiveData<HasilKonversi?> = hasilKonversi
}
