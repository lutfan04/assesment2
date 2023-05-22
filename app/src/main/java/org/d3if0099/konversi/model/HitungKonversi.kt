package org.d3if0099.konversi.model

import org.d3if0099.konversi.db.KonversiEntity

fun KonversiEntity.hitungKonversi(): HasilKonversi {
    val hasil = (celcius * 9 / 5) + 32
    return HasilKonversi(hasil)
}