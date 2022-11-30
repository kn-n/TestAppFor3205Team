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
import javax.inject.Inject

class DownloadsViewModel @Inject constructor(
    private val downloadRepositoriesInteractor: DownloadRepositoriesInteractor,
    private val gitHubRepositoriesInteractor: GitHubRepositoriesInteractor,
    private val router: Router
) : BaseViewModel() {

    private val _result = MutableLiveData<List<GitHubRepositoryEntity>>()
    val result: LiveData<List<GitHubRepositoryEntity>> = _result

    private val _response = MutableLiveData<ResponseBody>()
    val response: LiveData<ResponseBody> = _response

    fun getRepositoriesFromDatabase(query: String) {
        if (query.isEmpty()) {
            _result.postValue(downloadRepositoriesInteractor.getAllRepositories())
        } else {
            _result.postValue(downloadRepositoriesInteractor.getFoundRepositories(query))
        }
    }

    fun downloadZip(owner: String, repo: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _response.postValue(gitHubRepositoriesInteractor.downloadZip(owner, repo))
        }
    }

    fun backToSearch() {
        router.backTo(Screens.search())
    }
}