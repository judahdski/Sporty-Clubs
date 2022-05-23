package com.d3if0002.sportyclubs.ui.createclub

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.d3if0002.sportyclubs.db.ClubDao

class CreateDataViewModelFactory(
    private val db: ClubDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CreateDataViewModel::class.java)) {
            return CreateDataViewModel(db) as T
        }
        throw IllegalArgumentException("ViewModel class tidak diketahui")
    }
}