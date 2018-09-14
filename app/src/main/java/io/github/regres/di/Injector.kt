package io.github.regres.di

import dagger.Component
import io.github.regres.ui.main.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [])
interface Injector {
    fun inject(inject: MainActivity)
}