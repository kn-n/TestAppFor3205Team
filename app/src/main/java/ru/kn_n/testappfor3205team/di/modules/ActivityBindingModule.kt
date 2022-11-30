package ru.kn_n.testappfor3205team.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.kn_n.testappfor3205team.di.scopes.ActivityScoped
import ru.kn_n.testappfor3205team.presentation.MainActivity

@Module
abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(
        modules = [
            FragmentModule::class
        ])
    abstract fun providesMainActivity(): MainActivity
}