package ru.kn_n.testappfor3205team.di.modules

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.kn_n.testappfor3205team.data.api.GitHubRepositoriesAPI
import ru.kn_n.testappfor3205team.utils.Constants.BASE_URL
import javax.inject.Singleton

@Module
class RetrofitClientModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideGitHubRepositoriesAPI(retrofit: Retrofit): GitHubRepositoriesAPI = retrofit.create(GitHubRepositoriesAPI::class.java)
}