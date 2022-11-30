package ru.kn_n.testappfor3205team.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.kn_n.testappfor3205team.di.scopes.FragmentScoped
import ru.kn_n.testappfor3205team.presentation.fragment.DownloadsFragment
import ru.kn_n.testappfor3205team.presentation.fragment.SearchFragment

@Module
abstract class FragmentModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun searchFragment(): SearchFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun downloadsFragment(): DownloadsFragment
}