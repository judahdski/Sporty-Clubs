package com.d3if0002.sportyclubs.ui.clubdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.d3if0002.sportyclubs.db.ClubDao

class DetailClubViewModelFactory(
    private val db: ClubDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailClubViewModel::class.java)) {
            return DetailClubViewModel(db) as T
        }
        throw IllegalArgumentException("ViewModel class tidak diketahui")
    }
}