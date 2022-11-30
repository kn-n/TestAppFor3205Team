package ru.kn_n.testappfor3205team.data.repository

import ru.kn_n.testappfor3205team.data.api.GitHubRepositoriesAPI
import ru.kn_n.testappfor3205team.data.mapper.GitHubRepositoriesResponseMapper
import ru.kn_n.testappfor3205team.domain.repository.GitHubRepositoriesRepository
import javax.inject.Inject

class GitHubRepositoriesRepositoryImpl @Inject constructor(
    private val api: GitHubRepositoriesAPI,
    private val mapper: GitHubRepositoriesResponseMapper
) : GitHubRepositoriesRepository {
    override suspend fun getGitHubRepositories(query: String) = mapper.mapGitHubRepositoriesResponse(
        api.getSearchRepositories(query = query)
    )

    override suspend fun downloadZip(owner: String, repo: String) = api.downloadZip(owner, repo)
}