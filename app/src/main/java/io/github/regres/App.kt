package io.github.regres

import android.app.Application
import io.github.regres.di.DaggerInjector
import io.github.regres.di.Injector
import io.github.regres.di.modules.AppModule
import io.reactivex.plugins.RxJavaPlugins
import timber.log.Timber

class App: Application() {

    lateinit var injector: Injector

    override fun onCreate() {
        super.onCreate()
        initDagger()
        initTimber()
    }

    private fun initDagger() {
        this.injector = DaggerInjector.builder()
                .appModule(AppModule(this))
                .build()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            run {
                Timber.plant(Timber.DebugTree())
            }
        }
    }

    private fun initPluginsErrorHandler() = RxJavaPlugins.setErrorHandler { Timber.e(it) }

}