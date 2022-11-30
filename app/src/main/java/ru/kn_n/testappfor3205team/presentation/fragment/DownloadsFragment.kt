package ru.kn_n.testappfor3205team.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import ru.kn_n.testappfor3205team.R
import ru.kn_n.testappfor3205team.presentation.adapter.RecyclerViewAdapter
import ru.kn_n.testappfor3205team.presentation.base.BaseFragment
import ru.kn_n.testappfor3205team.presentation.viewmodel.DownloadsViewModel
import ru.kn_n.testappfor3205team.utils.*
import javax.inject.Inject

class DownloadsFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: DownloadsViewModel by viewModels { viewModelFactory }

    private val mainAdapter by lazy {
        RecyclerViewAdapter(
            onRepositoryClick = { link -> onRepositoryItemClick(link) },
            null,
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
        setUpSearchListener { nextText -> getRepositories(nextText) }

        getRepositories(String.EMPTY)
    }

    private fun setUpAppBar() {
        binding.apply {
            toDownloadsBtn.gone()
            backBtn.show()
            title.text = requireContext().getString(R.string.download)
            backBtn.setOnClickListener {
                viewModel.backToSearch()
            }
        }
    }

    private fun getRepositories(query: String) {
        viewModel.getRepositoriesFromDatabase(query)
        viewModel.result.observe(viewLifecycleOwner) {
            showLoading()
            showData(it, mainAdapter)
        }
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
}