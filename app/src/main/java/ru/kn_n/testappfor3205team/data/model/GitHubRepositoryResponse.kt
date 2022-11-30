package ru.kn_n.testappfor3205team.data.model

data class GitHubRepositoryResponse(
    val name: String?,
    val full_name: String?,
    val owner: GitHubRepositoryOwnerResponse,
    val html_url: String?,
    val description: String?,
    val stargazers_count: String?,
    val language: String?
)

data class GitHubRepositoryOwnerResponse(
    val login: String?,
    val avatar_url: String?
)