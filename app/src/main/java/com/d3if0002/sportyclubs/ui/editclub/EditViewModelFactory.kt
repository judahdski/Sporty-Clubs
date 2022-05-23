package com.d3if0002.sportyclubs.ui.editclub

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.d3if0002.sportyclubs.db.ClubDao

class EditViewModelFactory(
    private val db: ClubDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditViewModel::class.java)) {
            return EditViewModel(db) as T
        }
        throw IllegalArgumentException("EditViewModel class tidak diketahui")
    }
}