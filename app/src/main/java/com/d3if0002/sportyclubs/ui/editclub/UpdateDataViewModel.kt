package com.d3if0002.sportyclubs.ui.editclub

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d3if0002.sportyclubs.db.ClubDao
import com.d3if0002.sportyclubs.db.ClubEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.invoke
import kotlinx.coroutines.launch

class UpdateDataViewModel(private val db: ClubDao) : ViewModel() {
    fun updateClub(id: Long, kategori: String, linkGambar: String, julukan: String, namaPanjang: String, deskripsi: String): Boolean {
        val club = ClubEntity(
            id, kategori, linkGambar, julukan, namaPanjang, deskripsi
        )
        viewModelScope.launch {
            Dispatchers.IO{
                db.ubahDataClub(club)
            }
        }

        return true
    }

    fun getClubFromDB(value: Long): LiveData<ClubEntity> {
        return db.ambilSatuClub(value)
    }
}