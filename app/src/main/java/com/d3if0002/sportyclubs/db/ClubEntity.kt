package com.d3if0002.sportyclubs.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "club")
data class ClubEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val category: String,
    val imageLink: String,
    val nickname: String,
    val fullName: String,
    val description: String
)
