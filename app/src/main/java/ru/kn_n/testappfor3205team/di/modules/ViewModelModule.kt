package ru.kn_n.testappfor3205team.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.kn_n.testappfor3205team.di.scopes.AppScoped
import ru.kn_n.testappfor3205team.di.viewmodelfactory.ViewModelFactory
import ru.kn_n.testappfor3205team.di.viewmodelfactory.ViewModelKey
import ru.kn_n.testappfor3205team.presentation.viewmodel.DownloadsViewModel
import ru.kn_n.testappfor3205team.presentation.viewmodel.SearchViewModel

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindSearchViewModel(viewModel: SearchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DownloadsViewModel::class)
    abstract fun bindDownloadsViewModel(viewModel: DownloadsViewModel): ViewModel

    @Binds
    @AppScoped
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}