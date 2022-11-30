package ru.kn_n.testappfor3205team.domain.repository

import okhttp3.ResponseBody
import ru.kn_n.testappfor3205team.domain.model.GitHubRepositoryEntity

interface GitHubRepositoriesRepository {
    suspend fun getGitHubRepositories(query: String): List<GitHubRepositoryEntity>
    suspend fun downloadZip(owner: String, repo: String): ResponseBody
}