package io.github.regres.di

import dagger.Component
import io.github.regres.di.modules.AppModule
import io.github.regres.di.modules.DatabaseModule
import io.github.regres.di.modules.NetworkModule
import io.github.regres.di.modules.ViewModelModule
import io.github.regres.ui.main.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DatabaseModule::class, NetworkModule::class, ViewModelModule::class])
interface Injector {
    fun inject(inject: MainActivity)
}