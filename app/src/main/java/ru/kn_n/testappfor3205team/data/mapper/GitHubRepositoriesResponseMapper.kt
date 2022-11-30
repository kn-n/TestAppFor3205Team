package ru.kn_n.testappfor3205team.data.mapper

import ru.kn_n.testappfor3205team.data.model.GitHubRepositoryResponse
import ru.kn_n.testappfor3205team.domain.model.GitHubRepositoryEntity
import ru.kn_n.testappfor3205team.utils.EMPTY
import javax.inject.Inject

class GitHubRepositoriesResponseMapper @Inject constructor() {
    fun mapGitHubRepositoriesResponse(response: List<GitHubRepositoryResponse>): List<GitHubRepositoryEntity> {
        return response.map { mapGitHubRepositoryResponse(it) }
    }

    private fun mapGitHubRepositoryResponse(response: GitHubRepositoryResponse): GitHubRepositoryEntity {
        return GitHubRepositoryEntity(
            fullRepoName = response.full_name ?: String.EMPTY,
            repoName = response.name ?: String.EMPTY,
            login = response.owner.login ?: String.EMPTY,
            avatarUrl = response.owner.avatar_url ?: String.EMPTY,
            repoUrl = response.html_url ?: String.EMPTY,
            description = response.description ?: String.EMPTY,
            stars = response.stargazers_count ?: String.EMPTY,
            language = response.language ?: String.EMPTY
        )
    }
}