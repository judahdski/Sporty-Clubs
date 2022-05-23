package com.d3if0002.sportyclubs.ui.clubdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.d3if0002.sportyclubs.db.ClubDao
import com.d3if0002.sportyclubs.db.ClubEntity

class DetailClubViewModel(private val db: ClubDao) : ViewModel() {
    fun getClubFromDB(value: Long): LiveData<ClubEntity> {
        return db.ambilSatuClub(value)
    }
}