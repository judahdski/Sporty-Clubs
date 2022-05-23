package com.d3if0002.sportyclubs.ui.createclub

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d3if0002.sportyclubs.db.ClubDao
import com.d3if0002.sportyclubs.db.ClubEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CreateDataViewModel(private val db: ClubDao) : ViewModel() {

    fun tambahClub(kategori: String, linkGambar: String, julukan: String, namaPanjang: String, deskripsi: String) : Boolean {
        val clubEntity = ClubEntity(
            category = kategori,
            imageLink = linkGambar,
            nickname = julukan,
            fullName = namaPanjang,
            description = deskripsi
        )

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                db.tambahClub(clubEntity)
            }
        }

        return true
    }
}