package ru.kn_n.testappfor3205team.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import ru.kn_n.testappfor3205team.di.modules.ActivityBindingModule
import ru.kn_n.testappfor3205team.di.modules.AppModule
import ru.kn_n.testappfor3205team.di.scopes.AppScoped
import javax.inject.Singleton

@AppScoped
@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityBindingModule::class,
        AppModule::class
    ]
)
interface AppComponent: AndroidInjector<ru.kn_n.testappfor3205team.Application> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(application: ru.kn_n.testappfor3205team.Application)
}