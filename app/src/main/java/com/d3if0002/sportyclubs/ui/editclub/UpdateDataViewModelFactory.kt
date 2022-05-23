package com.d3if0002.sportyclubs.ui.editclub

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.d3if0002.sportyclubs.db.ClubDao
import java.lang.IllegalArgumentException

class UpdateDataViewModelFactory(private val db: ClubDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UpdateDataViewModel::class.java)) {
            return UpdateDataViewModel(db) as T
        }
        throw IllegalArgumentException("UpdateDataViewModel tidak dikenal")
    }
}