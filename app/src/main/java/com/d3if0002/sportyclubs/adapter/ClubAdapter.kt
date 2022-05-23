package com.d3if0002.sportyclubs.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.d3if0002.sportyclubs.databinding.LayoutClubListItemBinding
import com.d3if0002.sportyclubs.db.ClubEntity
import com.d3if0002.sportyclubs.ui.clublist.ClubListFragmentDirections

class ClubAdapter :
    RecyclerView.Adapter<ClubAdapter.ClubViewHolder>() {

    private val dataClub = mutableListOf<ClubEntity>()

    fun updateData(newData: List<ClubEntity>) {
        dataClub.clear()
        dataClub.addAll(newData)
        notifyDataSetChanged()
    }

    class ClubViewHolder(binding: LayoutClubListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val kategori = binding.kategori
        val nama = binding.namaKlub
        val tombolDetail = binding.detailButton
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClubViewHolder {
        val binding =
            LayoutClubListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ClubViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ClubViewHolder, position: Int) {
        val club = dataClub[position]

        with(holder) {
            kategori.text = club.category
            nama.text = club.fullName
            tombolDetail.setOnClickListener {
                val action =
                    ClubListFragmentDirections.actionClubListFragmentToDetailClubFragment(
                        club = club.id
                    )
                itemView.findNavController().navigate(action)
            }
        }
    }

    override fun getItemCount(): Int = dataClub.size
}