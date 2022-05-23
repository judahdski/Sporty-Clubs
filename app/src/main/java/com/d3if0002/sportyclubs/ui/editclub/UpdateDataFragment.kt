package com.d3if0002.sportyclubs.ui.editclub

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.d3if0002.sportyclubs.databinding.FragmentUpdateDataBinding
import com.d3if0002.sportyclubs.db.ClubDb
import com.d3if0002.sportyclubs.db.ClubEntity

class UpdateDataFragment : Fragment() {
    private val viewModel: UpdateDataViewModel by lazy {
        val db = ClubDb.getInstance(requireContext())
        val factory = UpdateDataViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[UpdateDataViewModel::class.java]
    }

    private lateinit var binding: FragmentUpdateDataBinding
    private val args: UpdateDataFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUpdateDataBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val clubId = args.clubId
        viewModel.getClubFromDB(clubId).observe(viewLifecycleOwner) {
            if (it != null)
                bindClubData(it)
        }
        binding.updateButton.setOnClickListener {
            updateData(clubId)
        }
    }

    private fun bindClubData(club: ClubEntity) {
        with(binding) {
            kategoriInput.editText?.text = SpannableStringBuilder(club.category)
            gambarInput.editText?.text = SpannableStringBuilder(club.imageLink)
            julukanInput.editText?.text = SpannableStringBuilder(club.nickname)
            namaLengkapInput.editText?.text = SpannableStringBuilder(club.fullName)
            deskripsiInput.editText?.text = SpannableStringBuilder(club.description)
        }
    }

    private fun updateData(clubId: Long) {
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

        val info =
            viewModel.updateClub(clubId, kategori, linkGambar, julukan, namaPanjang, deskripsi)
        if (info) {
            Toast.makeText(requireContext(), "Berhasil mengubah data", Toast.LENGTH_SHORT).show()
            val action = UpdateDataFragmentDirections.actionUpdateDataFragmentToClubListFragment()
            findNavController().navigate(action)
        }
    }
}