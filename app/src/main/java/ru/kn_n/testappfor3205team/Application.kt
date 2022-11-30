package ru.kn_n.testappfor3205team

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import ru.kn_n.testappfor3205team.di.component.DaggerAppComponent

class Application : DaggerApplication(){
    private val applicationInjector = DaggerAppComponent
        .builder()
        .application(this)
        .build()

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = applicationInjector
}