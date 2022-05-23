package com.d3if0002.sportyclubs.ui.clublist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.d3if0002.sportyclubs.db.ClubDao
import java.lang.IllegalArgumentException

class ClubListViewModelFactory(
    private val db: ClubDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ClubListViewModel::class.java)) {
            return ClubListViewModel(db) as T
        }
        throw IllegalArgumentException("ViewModel class tidak diketahui")
    }
}