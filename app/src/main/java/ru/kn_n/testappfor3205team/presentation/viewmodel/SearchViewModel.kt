package ru.kn_n.testappfor3205team.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import ru.kn_n.testappfor3205team.domain.interactor.DownloadRepositoriesInteractor
import ru.kn_n.testappfor3205team.domain.interactor.GitHubRepositoriesInteractor
import ru.kn_n.testappfor3205team.domain.model.GitHubRepositoryEntity
import ru.kn_n.testappfor3205team.presentation.base.BaseViewModel
import ru.kn_n.testappfor3205team.presentation.navigation.Screens
import ru.kn_n.testappfor3205team.utils.Resource
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val gitHubRepositoriesInteractor: GitHubRepositoriesInteractor,
    private val downloadRepositoriesInteractor: DownloadRepositoriesInteractor,
    private val router: Router
) : BaseViewModel() {

    private val _result = MutableLiveData<Resource<List<GitHubRepositoryEntity>>>()
    val result: LiveData<Resource<List<GitHubRepositoryEntity>>> = _result

    private val _response = MutableLiveData<ResponseBody>()
    val response: LiveData<ResponseBody> = _response

    fun getGitHubRepositories(query: String) {
        requestWithLiveData(_result) {
            gitHubRepositoriesInteractor.getGitHubRepositories(query).onEach {
                it.isDownloaded = downloadRepositoriesInteractor.isInDatabase(it.fullRepoName)
            }
        }
    }

    fun addRepositoryToDownloads(repository: GitHubRepositoryEntity) {
        downloadRepositoriesInteractor.addRepository(repository)
    }

    fun downloadZip(owner: String, repo: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _response.postValue(gitHubRepositoriesInteractor.downloadZip(owner, repo))
        }
    }

    fun routToDownloads() {
        router.navigateTo(Screens.downloads())
    }
}