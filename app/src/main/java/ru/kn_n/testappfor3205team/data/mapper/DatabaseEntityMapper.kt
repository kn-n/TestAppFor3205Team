package ru.kn_n.testappfor3205team.data.mapper

import ru.kn_n.testappfor3205team.data.database.DownloadRepositoryEntity
import ru.kn_n.testappfor3205team.domain.model.GitHubRepositoryEntity
import javax.inject.Inject

class DatabaseEntityMapper @Inject constructor() {
    fun mapDatabaseEntity(data: DownloadRepositoryEntity): GitHubRepositoryEntity{
        return GitHubRepositoryEntity(
            fullRepoName = data.fullRepoName,
            login = data.login,
            repoName = data.repoName,
            avatarUrl = data.avatarUrl,
            repoUrl = data.repoUrl,
            description = data.description,
            stars = data.stars,
            language = data.language,
            isDownloaded = true
        )
    }

    fun mapUIEntity(data: GitHubRepositoryEntity): DownloadRepositoryEntity{
        return DownloadRepositoryEntity(
            fullRepoName = data.fullRepoName,
            login = data.login,
            repoName = data.repoName,
            avatarUrl = data.avatarUrl,
            repoUrl = data.repoUrl,
            description = data.description,
            stars = data.stars,
            language = data.language
        )
    }
}