package ru.kn_n.testappfor3205team.domain.model

import ru.kn_n.testappfor3205team.utils.EMPTY

data class GitHubRepositoryEntity(
    val login: String = String.EMPTY,
    val avatarUrl: String = String.EMPTY,
    val fullRepoName: String = String.EMPTY,
    val repoName: String = String.EMPTY,
    val repoUrl: String = String.EMPTY,
    val description: String = String.EMPTY,
    val stars: String = String.EMPTY,
    val language: String = String.EMPTY,
    var isDownloaded: Boolean = false
)
