package org.d3if0099.konversi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.d3if0099.konversi.model.HasilKonversi

class KonversiViewModel : ViewModel() {

    private val hasilKonversi = MutableLiveData<HasilKonversi?>()
    fun hitungKonversi(celsius: Int) {
        val hasil = (celsius * 9 / 5) + 32
        hasilKonversi.value = HasilKonversi(hasil)
    }

    fun getHasilKonversi(): LiveData<HasilKonversi?> = hasilKonversi
}



