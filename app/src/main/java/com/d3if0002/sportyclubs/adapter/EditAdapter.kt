package com.d3if0002.sportyclubs.adapter

import android.R
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.d3if0002.sportyclubs.databinding.LayoutEditItemBinding
import com.d3if0002.sportyclubs.db.ClubEntity
import com.d3if0002.sportyclubs.ui.editclub.EditFragmentDirections
import com.d3if0002.sportyclubs.ui.editclub.EditViewModel


class EditAdapter(private val context: Context, private val viewModel: EditViewModel) :
    RecyclerView.Adapter<EditAdapter.EditViewHolder>() {

    private val dataClubs = mutableListOf<ClubEntity>()

    fun updateData(data: List<ClubEntity>) {
        dataClubs.clear()
        dataClubs.addAll(data)
        notifyDataSetChanged()
    }

    class EditViewHolder(binding: LayoutEditItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val kategori = binding.kategori
        val namaCLub = binding.namaKlub
        val deleteBtn = binding.deleteOneButton
        val updateBtn = binding.editButton
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EditViewHolder {
        val binding =
            LayoutEditItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EditViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EditViewHolder, position: Int) {
        val item = dataClubs[position]

        with(holder) {
            kategori.text = item.category
            namaCLub.text = item.fullName
            deleteBtn.setOnClickListener {
                AlertDialog.Builder(context)
                    .setTitle("Konfimasi hapus ${item.fullName}")
                    .setMessage("Apakah kamu yakin mau menghapus ${item.fullName}?")
                    .setPositiveButton(
                        "Hapus"
                    ) { _, _ ->
                        viewModel.hapusSatuClub(item)
                    }
                    .setNegativeButton("Tidak jadi", null)
                    .setIcon(R.drawable.ic_delete)
                    .show()
            }
            updateBtn.setOnClickListener {
                val action = EditFragmentDirections.actionEditFragmentToUpdateDataFragment(item.id)
                itemView.findNavController().navigate(action)
            }
        }
    }

    override fun getItemCount() = dataClubs.size
}