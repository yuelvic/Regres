package io.github.regres.di.modules

import dagger.Module
import dagger.Provides
import io.github.regres.App
import javax.inject.Singleton

@Module
class AppModule(private val app: App) {
    @Provides
    @Singleton
    fun provideApp(): App = app
}