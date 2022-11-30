package ru.kn_n.testappfor3205team.di.modules

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

@Module(
    includes = [
        ViewModelModule::class,
        RepositoriesModule::class,
        NavigationModule::class
    ]
)
abstract class AppModule {
    @Binds
    abstract fun bindContext(application: Application): Context
}