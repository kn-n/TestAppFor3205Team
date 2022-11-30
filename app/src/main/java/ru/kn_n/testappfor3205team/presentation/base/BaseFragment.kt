package ru.kn_n.testappfor3205team.presentation.base

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.kn_n.testappfor3205team.R
import ru.kn_n.testappfor3205team.databinding.FragmentMainBinding
import ru.kn_n.testappfor3205team.domain.model.GitHubRepositoryEntity
import ru.kn_n.testappfor3205team.presentation.adapter.RecyclerViewAdapter
import ru.kn_n.testappfor3205team.utils.Constants.DELAY
import ru.kn_n.testappfor3205team.utils.Resource
import ru.kn_n.testappfor3205team.utils.Status
import ru.kn_n.testappfor3205team.utils.gone
import ru.kn_n.testappfor3205team.utils.show
import java.time.LocalDate

abstract class BaseFragment : DaggerFragment() {

    private var _binding: FragmentMainBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun <T> showRequestResult(
        resource: Resource<T>,
        doOnSuccess: () -> Unit,
        doOnError: () -> Unit,
        doOnLoading: () -> Unit
    ) {
        when (resource.status) {
            Status.LOADING -> doOnLoading()
            Status.SUCCESS -> doOnSuccess()
            Status.ERROR -> doOnError()
        }
    }

    private val requestPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                Toast.makeText(
                    requireContext(),
                    requireContext().getString(R.string.permissionGranted),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    requireContext(),
                    requireContext().getString(R.string.permissionDenied),
                    Toast.LENGTH_SHORT
                ).show()
            }

        }

    fun setUpSearchListener(doOnSearch: (nextText: String) -> Unit) {
        binding.search.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {

                private var queryTextChangedJob: Job? = null

                override fun onQueryTextSubmit(text: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    queryTextChangedJob?.cancel()
                    queryTextChangedJob = lifecycleScope.launch(Dispatchers.Main) {
                        newText?.let {
                            delay(DELAY)
                            doOnSearch(newText)
                        }
                    }
                    return false
                }
            }
        )
    }

    fun setUpAdapter(mAdapter: RecyclerViewAdapter) {
        binding.recyclerView.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            itemAnimator = DefaultItemAnimator()
        }
    }

    fun showLoading() {
        binding.apply {
            error.gone()
            empty.gone()
            recyclerView.gone()
            loading.show()
        }
    }

    fun showData(
        data: List<GitHubRepositoryEntity>,
        mAdapter: RecyclerViewAdapter
    ) {
        binding.apply {
            loading.gone()
            error.gone()
            if (data.isEmpty()) {
                empty.show()
                recyclerView.gone()
            } else {
                empty.gone()
                recyclerView.show()
                mAdapter.setItems(data)
            }
        }
    }

    fun downloadRepository(
        owner: String,
        repo: String,
        downloadZip: (
            owner: String,
            repo: String,
            fileName: String
        ) -> Unit
    ) {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            val fileName = "${owner}-${repo}-${LocalDate.now()}.zip"

            downloadZip(owner, repo, fileName)

        } else {
            requestPermission.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
    }

    fun onRepositoryItemClick(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}