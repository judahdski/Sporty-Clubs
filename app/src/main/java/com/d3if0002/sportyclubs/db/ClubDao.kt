package com.d3if0002.sportyclubs.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ClubDao {
    @Insert
    fun tambahClub(club: ClubEntity)

    @Query("SELECT * FROM club WHERE id = :clubID")
    fun ambilSatuClub(clubID: Long): LiveData<ClubEntity>

    @Query("SELECT * FROM club")
    fun ambilSemuaClub(): LiveData<List<ClubEntity>>

    @Query("DELETE FROM club")
    fun hapusSemua()

    @Delete
    fun hapusSatuClub(club: ClubEntity)

    @Update
    fun ubahDataClub(club: ClubEntity)
}