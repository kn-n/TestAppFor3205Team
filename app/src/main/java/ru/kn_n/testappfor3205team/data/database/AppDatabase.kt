package ru.kn_n.testappfor3205team.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DownloadRepositoryEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getDownloadRepositoryDao(): DownloadRepositoryDao
}