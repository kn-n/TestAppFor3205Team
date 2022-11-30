package ru.kn_n.testappfor3205team.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DownloadRepositoryDao {
    @Query("SELECT * FROM downloads")
    fun getDownloadRepositories(): List<DownloadRepositoryEntity>

    @Insert
    fun insertRepository(downloadRepositoryEntity: DownloadRepositoryEntity)

    @Query("SELECT EXISTS (SELECT * FROM downloads WHERE fullRepoName = :fullRepoName)")
    fun isInDatabase(fullRepoName: String): Boolean

    @Query("SELECT * FROM downloads WHERE login LIKE '%' || :query || '%'")
    fun getFilteredDownloadRepositories(query: String): List<DownloadRepositoryEntity>
}