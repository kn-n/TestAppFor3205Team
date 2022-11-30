package ru.kn_n.testappfor3205team.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.kn_n.testappfor3205team.utils.EMPTY

@Entity(tableName = "downloads")
data class DownloadRepositoryEntity(
    @ColumnInfo(name = "login")
    val login: String = String.EMPTY,
    @ColumnInfo(name = "avatarUrl")
    val avatarUrl: String = String.EMPTY,
    @ColumnInfo(name = "fullRepoName")
    val fullRepoName: String = String.EMPTY,
    @ColumnInfo(name = "repoName")
    val repoName: String = String.EMPTY,
    @ColumnInfo(name = "repoUrl")
    val repoUrl: String = String.EMPTY,
    @ColumnInfo(name = "description")
    val description: String = String.EMPTY,
    @ColumnInfo(name = "stars")
    val stars: String = String.EMPTY,
    @ColumnInfo(name = "language")
    val language: String = String.EMPTY
) {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}
