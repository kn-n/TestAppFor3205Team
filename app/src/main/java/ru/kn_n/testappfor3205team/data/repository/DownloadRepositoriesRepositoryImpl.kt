package ru.kn_n.testappfor3205team.data.repository

import ru.kn_n.testappfor3205team.data.database.DownloadRepositoryDao
import ru.kn_n.testappfor3205team.data.mapper.DatabaseEntityMapper
import ru.kn_n.testappfor3205team.domain.model.GitHubRepositoryEntity
import ru.kn_n.testappfor3205team.domain.repository.DownloadRepositoriesRepository
import javax.inject.Inject

class DownloadRepositoriesRepositoryImpl @Inject constructor(
    private val downloadRepositoryDao: DownloadRepositoryDao,
    private val mapper: DatabaseEntityMapper
) : DownloadRepositoriesRepository {

    override fun getAllRepositories() =
        downloadRepositoryDao.getDownloadRepositories().map { mapper.mapDatabaseEntity(it) }

    override fun addRepository(repository: GitHubRepositoryEntity) {
        downloadRepositoryDao.insertRepository(mapper.mapUIEntity(repository))
    }

    override fun isInDatabase(name: String) = downloadRepositoryDao.isInDatabase(name)

    override fun getFoundRepositories(query: String) =
        downloadRepositoryDao
            .getFilteredDownloadRepositories(query)
            .map {
                mapper.mapDatabaseEntity(it)
            }
}