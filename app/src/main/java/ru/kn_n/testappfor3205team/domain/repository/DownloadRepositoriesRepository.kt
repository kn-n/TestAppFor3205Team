package ru.kn_n.testappfor3205team.domain.repository

import ru.kn_n.testappfor3205team.domain.model.GitHubRepositoryEntity

interface DownloadRepositoriesRepository {
    fun getAllRepositories(): List<GitHubRepositoryEntity>
    fun addRepository(repository: GitHubRepositoryEntity)
    fun isInDatabase(name: String): Boolean
    fun getFoundRepositories(query: String): List<GitHubRepositoryEntity>
}