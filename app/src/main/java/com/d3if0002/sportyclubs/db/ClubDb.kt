package com.d3if0002.sportyclubs.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ClubEntity::class], version = 2, exportSchema = false)
abstract class ClubDb : RoomDatabase() {

    abstract val dao: ClubDao

    companion object {
        @Volatile
        private var INSTANCE: ClubDb? = null
        fun getInstance(context: Context): ClubDb {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ClubDb::class.java,
                        "bmi.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}