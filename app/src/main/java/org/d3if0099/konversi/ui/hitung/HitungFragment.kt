package org.d3if0099.konversi.ui.hitung

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import org.d3if0099.konversi.R
import org.d3if0099.konversi.databinding.FragmentHitungBinding
import org.d3if0099.konversi.db.KonversiDb
import org.d3if0099.konversi.model.HasilKonversi


class HitungFragment : Fragment() {


    private lateinit var binding: FragmentHitungBinding


    private val viewModel: HitungViewModel by lazy {
        val db = KonversiDb.getInstance(requireContext())
        val factory = HitungViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[HitungViewModel::class.java]


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_about) {
            findNavController().navigate(
                R.id.action_hitungFragment_to_aboutFragment
            )
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHitungBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.button.setOnClickListener { hitungKonversi() }
        binding.shareButton.setOnClickListener { shareData() }

        viewModel.getHasilKonversi().observe(requireActivity(), { showResult(it) })
        viewModel.data.observe(viewLifecycleOwner, {
            if (it == null) return@observe
            Log.d("HitungFragment", "Data tersimpan. ID = ${it.id}")
        })

    }

    private fun showResult(result: HasilKonversi?) {
        if (result == null) return
        binding.fahrenheitTextView.text = getString(R.string.total, result.total.toString())
        binding.buttonGroup.visibility = View.VISIBLE
    }

    private fun hitungKonversi() {
        val celcius = binding.celciusEditText.text.toString()
        val fahrenheit = binding.fahrenheitTextView.toString()
        if (TextUtils.isEmpty(celcius)) {
            Toast.makeText(context, R.string.celcius_invalid, Toast.LENGTH_LONG).show()
            return
        }
        viewModel.hitungKonversi(
            celcius.toInt(),
            fahrenheit.toInt(),
        )
    }

    private fun shareData() {
        val message = getString(
            R.string.bagikan_template,
            binding.celciusEditText.text,
            binding.fahrenheitTextView.text
        )
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, message)
        if (shareIntent.resolveActivity(
                requireActivity().packageManager
            ) != null
        ) {
            startActivity(shareIntent)
        }
    }
}