package com.d3if0002.sportyclubs.ui.editclub

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d3if0002.sportyclubs.db.ClubDao
import com.d3if0002.sportyclubs.db.ClubEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.invoke
import kotlinx.coroutines.launch

class EditViewModel(private val db: ClubDao) : ViewModel() {
    private val data = db.ambilSemuaClub()
    fun getClubData() = data

    fun hapusSemuaClub() {
        viewModelScope.launch {
            Dispatchers.IO{
                db.hapusSemua()
            }
        }
    }

    fun hapusSatuClub(club: ClubEntity) {
        viewModelScope.launch {
            Dispatchers.IO{
                db.hapusSatuClub(club)
            }
        }
    }
}