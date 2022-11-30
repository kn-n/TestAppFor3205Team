package ru.kn_n.testappfor3205team.di.modules

import dagger.Binds
import dagger.Module
import ru.kn_n.testappfor3205team.data.repository.DownloadRepositoriesRepositoryImpl
import ru.kn_n.testappfor3205team.data.repository.GitHubRepositoriesRepositoryImpl
import ru.kn_n.testappfor3205team.domain.repository.DownloadRepositoriesRepository
import ru.kn_n.testappfor3205team.domain.repository.GitHubRepositoriesRepository
import javax.inject.Singleton

@Module(
    includes = [
        RetrofitClientModule::class,
        RoomModule::class
    ]
)
abstract class RepositoriesModule {

    @Singleton
    @Binds
    abstract fun bindGitHubRepositoriesRepository(
        gitHubRepositoriesRepository: GitHubRepositoriesRepositoryImpl
    ): GitHubRepositoriesRepository

    @Singleton
    @Binds
    abstract fun bindDownloadRepositoriesRepository(
        downloadRepositoriesRepository: DownloadRepositoriesRepositoryImpl
    ): DownloadRepositoriesRepository
}