package shevtsov.daniil.incrementalreader.core.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import shevtsov.daniil.incrementalreader.core.di.AppScope
import shevtsov.daniil.incrementalreader.creation.CreationViewModel
import shevtsov.daniil.incrementalreader.main.MainViewModel

@Module
interface ViewModelModule {

    @Binds
    @AppScope
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindFragmentAViewModel(viewModelMain: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CreationViewModel::class)
    fun bindFragmentBViewModel(viewModel: CreationViewModel): ViewModel

}
