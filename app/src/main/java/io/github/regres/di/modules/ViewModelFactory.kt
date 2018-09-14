package io.github.regres.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.github.regres.ui.main.MainViewModel
import io.github.regres.utils.ViewModelKey

@Module
abstract class ViewModelFactory {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun bindMainActivity(mainViewModel: MainViewModel): ViewModel

}