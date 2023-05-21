package org.d3if0099.konversi.ui

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import org.d3if0099.konversi.R
import org.d3if0099.konversi.databinding.FragmentHitungBinding
import org.d3if0099.konversi.model.HasilKonversi


class HitungFragment : Fragment() {


    private lateinit var binding: FragmentHitungBinding


    private val viewModel: KonversiViewModel by lazy {
        ViewModelProvider(requireActivity())[KonversiViewModel::class.java]

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        binding = FragmentHitungBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.button.setOnClickListener { hitungKonversi() }
        viewModel.getHasilKonversi().observe(requireActivity(), { showResult(it) })
    }
    private fun hitungKonversi() {
        val celcius = binding.hargaEditText.text.toString()

        if (TextUtils.isEmpty(celcius)) {
            Toast.makeText(context, R.string.celcius_invalid, Toast.LENGTH_LONG).show()
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