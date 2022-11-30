package ru.kn_n.testappfor3205team.data.api

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.kn_n.testappfor3205team.data.model.GitHubRepositoryResponse

interface GitHubRepositoriesAPI {
    @GET("/users/{username}/repos")
    suspend fun getSearchRepositories(
        @Path("username") query: String,
        @Query("per_page") perPage: Int = 100
    ): List<GitHubRepositoryResponse>

    @GET("/repos/{owner}/{repo}/zipball/master")
    suspend fun downloadZip(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): ResponseBody
}