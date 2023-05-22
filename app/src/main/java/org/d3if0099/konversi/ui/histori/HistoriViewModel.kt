package org.d3if0099.konversi.ui.histori

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if0099.konversi.db.KonversiDao

class HistoriViewModel(private val db: KonversiDao) : ViewModel() {
    val data = db.getLastKonversi()
    fun hapusData() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            db.clearData()
        }
    }

}