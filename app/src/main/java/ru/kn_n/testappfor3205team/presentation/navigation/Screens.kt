package ru.kn_n.testappfor3205team.presentation.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.kn_n.testappfor3205team.presentation.fragment.DownloadsFragment
import ru.kn_n.testappfor3205team.presentation.fragment.SearchFragment

object Screens {
    fun search() = FragmentScreen { SearchFragment() }
    fun downloads() = FragmentScreen { DownloadsFragment() }
}