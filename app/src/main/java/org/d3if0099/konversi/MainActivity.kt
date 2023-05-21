package org.d3if0099.konversi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import org.d3if0099.konversi.databinding.ActivityMainBinding
import org.d3if0099.konversi.model.HasilKonversi


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: KonversiViewModel by lazy {
        ViewModelProvider(this)[KonversiViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener { hitungKonversi() }
        viewModel.getHasilKonversi().observe(this, { showResult(it) })
    }

    private fun hitungKonversi() {
        val celcius = binding.hargaEditText.text.toString()

        if (TextUtils.isEmpty(celcius)) {
            Toast.makeText(this, R.string.harga_invalid, Toast.LENGTH_LONG).show()
            return
        }
        viewModel.hitungKonversi(
            celcius.toInt(),
        )
    }
    private fun showResult(result: HasilKonversi?) {
        if (result == null) return
        binding.totalTextView.text = getString(R.string.total, result.total.toString())
    }
}