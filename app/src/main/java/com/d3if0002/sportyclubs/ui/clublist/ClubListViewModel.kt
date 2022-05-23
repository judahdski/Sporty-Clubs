package com.d3if0002.sportyclubs.ui.clublist

import androidx.lifecycle.ViewModel
import com.d3if0002.sportyclubs.db.ClubDao

class ClubListViewModel(private val db: ClubDao) : ViewModel() {
    private val data = db.ambilSemuaClub()
    fun getClubs() = data
}