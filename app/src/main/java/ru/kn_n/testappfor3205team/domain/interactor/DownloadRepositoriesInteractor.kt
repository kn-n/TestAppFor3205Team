package ru.kn_n.testappfor3205team.domain.interactor

import ru.kn_n.testappfor3205team.data.repository.DownloadRepositoriesRepositoryImpl
import ru.kn_n.testappfor3205team.domain.model.GitHubRepositoryEntity
import javax.inject.Inject

class DownloadRepositoriesInteractor @Inject constructor(
    private val downloadRepositoriesRepository: DownloadRepositoriesRepositoryImpl
) {
    fun getAllRepositories() = downloadRepositoriesRepository.getAllRepositories()

    fun addRepository(repository: GitHubRepositoryEntity) {
        downloadRepositoriesRepository.addRepository(repository)
    }

    fun isInDatabase(name: String) = downloadRepositoriesRepository.isInDatabase(name)

    fun getFoundRepositories(query: String) = downloadRepositoriesRepository.getFoundRepositories(query)
}