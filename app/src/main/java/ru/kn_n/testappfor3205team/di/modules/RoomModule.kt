package ru.kn_n.testappfor3205team.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.kn_n.testappfor3205team.data.database.AppDatabase
import ru.kn_n.testappfor3205team.data.database.DownloadRepositoryDao
import javax.inject.Singleton

@Module
class RoomModule {
    @Singleton
    @Provides
    fun getDownloadRepositoryDao(appDatabase: AppDatabase): DownloadRepositoryDao {
        return appDatabase.getDownloadRepositoryDao()
    }

    @Singleton
    @Provides
    fun getRoomDbInstance(context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "downloads_database").allowMainThreadQueries().build()
}