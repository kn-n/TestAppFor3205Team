package ru.kn_n.testappfor3205team.domain.interactor

import ru.kn_n.testappfor3205team.data.repository.GitHubRepositoriesRepositoryImpl
import javax.inject.Inject

class GitHubRepositoriesInteractor @Inject constructor(
    private val gitHubRepositoriesRepository: GitHubRepositoriesRepositoryImpl
) {
    suspend fun getGitHubRepositories(query: String) = gitHubRepositoriesRepository.getGitHubRepositories(query)

    suspend fun downloadZip(owner: String, repo: String) = gitHubRepositoriesRepository.downloadZip(owner, repo)
}