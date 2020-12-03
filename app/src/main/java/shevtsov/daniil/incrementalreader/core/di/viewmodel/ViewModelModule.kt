package shevtsov.daniil.incrementalreader.core.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import shevtsov.daniil.incrementalreader.core.di.AppScope
import shevtsov.daniil.incrementalreader.main.MainFragmentViewModel
import shevtsov.daniil.incrementalreader.creation.CreationFragmentViewModel

@Module
interface ViewModelModule {

    @Binds
    @AppScope
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainFragmentViewModel::class)
    fun bindFragmentAViewModel(viewModelMain: MainFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CreationFragmentViewModel::class)
    fun bindFragmentBViewModel(viewModel: CreationFragmentViewModel): ViewModel

}
