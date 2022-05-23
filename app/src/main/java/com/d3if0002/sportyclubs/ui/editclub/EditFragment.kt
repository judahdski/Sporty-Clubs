package com.d3if0002.sportyclubs.ui.editclub

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.d3if0002.sportyclubs.R
import com.d3if0002.sportyclubs.adapter.EditAdapter
import com.d3if0002.sportyclubs.databinding.FragmentEditBinding
import com.d3if0002.sportyclubs.db.ClubDb

class EditFragment : Fragment() {
    val viewModel: EditViewModel by lazy {
        val db = ClubDb.getInstance(requireActivity().applicationContext)
        val factory = EditViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[EditViewModel::class.java]
    }

    private lateinit var binding: FragmentEditBinding
    private lateinit var myAdapter: EditAdapter
    private var isDataNull: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditBinding.inflate(inflater, container, false)

        myAdapter = EditAdapter(requireContext(), viewModel)
        binding.editRecyclerView.adapter = myAdapter
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getClubData().observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                isDataNull = false
            }
            myAdapter.updateData(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.layout_edit_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_hapus -> {
                if (isDataNull) {
                    Toast.makeText(
                        requireContext(),
                        "Tidak ada data yang bisa dihapus",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    AlertDialog.Builder(context)
                        .setTitle("Konfimasi hapus semua")
                        .setMessage("Apakah kamu yakin mau menghapus semua club?")
                        .setPositiveButton(
                            "Hapus"
                        ) { _, _ ->
                            viewModel.hapusSemuaClub()
                        }
                        .setNegativeButton("Tidak jadi", null)
                        .setIcon(android.R.drawable.ic_delete)
                        .show()
                }

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}