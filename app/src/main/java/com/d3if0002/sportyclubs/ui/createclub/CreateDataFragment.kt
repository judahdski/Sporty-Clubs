package com.d3if0002.sportyclubs.ui.createclub

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.d3if0002.sportyclubs.databinding.FragmentCreateDataBinding
import com.d3if0002.sportyclubs.db.ClubDb

class CreateDataFragment : Fragment() {

    private val viewModel: CreateDataViewModel by lazy {
        val db = ClubDb.getInstance(requireContext())
        val factory = CreateDataViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[CreateDataViewModel::class.java]
    }

    private lateinit var binding: FragmentCreateDataBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.createButton.setOnClickListener { tambahClub() }
    }

    private fun tambahClub() {
        val kategori = binding.kategoriInput.editText?.text.toString()
        if (TextUtils.isEmpty(kategori)) {
            Toast.makeText(context, "Kategori invalid", Toast.LENGTH_LONG).show()
            return
        }

        val linkGambar = binding.gambarInput.editText?.text.toString()
        if (TextUtils.isEmpty(linkGambar)) {
            Toast.makeText(context, "Link gambar invalid", Toast.LENGTH_LONG).show()
            return
        }

        val julukan = binding.julukanInput.editText?.text.toString()
        if (TextUtils.isEmpty(julukan)) {
            Toast.makeText(context, "Julukan invalid", Toast.LENGTH_LONG).show()
            return
        }

        val namaPanjang = binding.namaLengkapInput.editText?.text.toString()
        if (TextUtils.isEmpty(namaPanjang)) {
            Toast.makeText(context, "Nama panjang invalid", Toast.LENGTH_LONG).show()
            return
        }

        val deskripsi = binding.deskripsiInput.editText?.text.toString()
        if (TextUtils.isEmpty(deskripsi)) {
            Toast.makeText(context, "Deskripsi invalid", Toast.LENGTH_LONG).show()
            return
        }

        val info = viewModel.tambahClub(kategori, linkGambar, julukan, namaPanjang, deskripsi)
        if (info) {
            Toast.makeText(requireContext(), "Berhasil menambahkan data", Toast.LENGTH_SHORT).show()
            val action = CreateDataFragmentDirections.actionCreateDataFragmentToClubListFragment()
            findNavController().navigate(action)
        }
    }
}