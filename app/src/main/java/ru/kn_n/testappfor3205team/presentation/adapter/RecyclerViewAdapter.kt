package ru.kn_n.testappfor3205team.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.appcompat.view.ContextThemeWrapper
import androidx.recyclerview.widget.RecyclerView
import ru.kn_n.testappfor3205team.R
import ru.kn_n.testappfor3205team.databinding.ItemRepositoryBinding
import ru.kn_n.testappfor3205team.domain.model.GitHubRepositoryEntity
import ru.kn_n.testappfor3205team.utils.loadImage
import ru.kn_n.testappfor3205team.utils.showOrHide

class RecyclerViewAdapter(
    private val onRepositoryClick: (link: String) -> Unit,
    private val saveRepository: ((repository: GitHubRepositoryEntity) -> Unit)?,
    private val downloadRepository: (owner: String, repo: String) -> Unit
) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private val items = ArrayList<GitHubRepositoryEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.ViewHolder {
        val binding = ItemRepositoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) {
        holder.bind(items[position], position)
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(
        private val binding: ItemRepositoryBinding,
        private val context: Context
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: GitHubRepositoryEntity, position: Int) {
            binding.image.loadImage(data.avatarUrl)
            binding.nickname.text = data.login
            binding.repositoryName.text = data.fullRepoName
            binding.repositoryDescription.text = data.description
            binding.stars.text = data.stars
            binding.iconCode.showOrHide(data.language.isNotEmpty())
            binding.language.text = data.language

            binding.menu.setOnClickListener {
                val wrapper = ContextThemeWrapper(context, R.style.popupMenuStyle)
                val popup = PopupMenu(wrapper, binding.menu)
                popup.inflate(R.menu.menu_options)
                if (data.isDownloaded) {
                    popup.menu.getItem(SAVE_MENU_ITEM_ID).isVisible = false
                }
                popup.setOnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.save_menu_item -> {
                            if (data.isDownloaded.not()) {
                                saveRepository?.let { it1 -> it1(data) }
                                data.isDownloaded = true
                                notifyItemChanged(position)
                            }}
                        R.id.download_menu_item -> {
                            downloadRepository(data.login, data.repoName)
                        }
                    }
                    false
                }
                popup.show()
            }

            binding.repoCard.setOnClickListener {
                onRepositoryClick(data.repoUrl)
            }
        }
    }

    fun setItems(data: List<GitHubRepositoryEntity>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

    companion object{
        private const val SAVE_MENU_ITEM_ID = 0
    }
}