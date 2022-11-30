package ru.kn_n.testappfor3205team.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import ru.kn_n.testappfor3205team.R
import ru.kn_n.testappfor3205team.domain.model.GitHubRepositoryEntity
import ru.kn_n.testappfor3205team.presentation.adapter.RecyclerViewAdapter
import ru.kn_n.testappfor3205team.presentation.base.BaseFragment
import ru.kn_n.testappfor3205team.presentation.viewmodel.SearchViewModel
import ru.kn_n.testappfor3205team.utils.*
import javax.inject.Inject

class SearchFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: SearchViewModel by viewModels { viewModelFactory }

    private val mainAdapter by lazy {
        RecyclerViewAdapter(
            onRepositoryClick = { link -> onRepositoryItemClick(link) },
            saveRepository = { repository -> saveRepository(repository) },
            downloadRepository = { owner, repo ->
                downloadRepository(owner, repo) { owner, repo, fileName ->
                    downloadZip(owner, repo, fileName)
                }
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpAppBar()
        setUpAdapter(mainAdapter)
        setUpRefreshListener()
        setUpSearchListener { nextText -> getRepositories(nextText) }
    }

    private fun setUpRefreshListener() {
        binding.refresh.setOnClickListener {
            getRepositories(binding.search.query.toString())
        }
    }

    private fun setUpAppBar() {
        binding.apply {
            toDownloadsBtn.show()
            backBtn.gone()
            title.text = requireContext().getString(R.string.search)
            toDownloadsBtn.setOnClickListener {
                viewModel.routToDownloads()
            }
        }
    }

    private fun getRepositories(query: String) {
        viewModel.getGitHubRepositories(query)
        viewModel.result.observe(viewLifecycleOwner) {
            showResult(it)
        }
    }

    private fun showResult(resource: Resource<List<GitHubRepositoryEntity>>) {
        resource.let {
            showRequestResult(
                resource = it,
                doOnSuccess = { it.data?.let { data -> showData(data, mainAdapter) } },
                doOnLoading = { showLoading() },
                doOnError = { showError(it.message.toString()) }
            )
        }
    }

    private fun showError(message: String) {
        binding.apply {
            recyclerView.gone()
            loading.gone()
            if (message == NOT_FOUND) {
                error.gone()
                empty.show()
            } else {
                empty.gone()
                error.show()
            }
        }
    }

    private fun saveRepository(repository: GitHubRepositoryEntity) {
        viewModel.addRepositoryToDownloads(repository)
    }

    private fun downloadZip(owner: String, repo: String, fileName: String) {
        viewModel.downloadZip(owner, repo)
        viewModel.response.observe(viewLifecycleOwner) {
            it?.let {
                val file = prepareToWritingFile(fileName, requireContext())
                it.writeToStream(file, requireContext())
            }
        }
    }

    companion object {
        private const val NOT_FOUND: String = "HTTP 404 "
    }
}