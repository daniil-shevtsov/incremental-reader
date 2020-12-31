package shevtsov.daniil.incrementalreader.core.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import shevtsov.daniil.incrementalreader.core.di.AppScope
import shevtsov.daniil.incrementalreader.creation.CreationViewModel
import shevtsov.daniil.incrementalreader.main.MainViewModel
import shevtsov.daniil.incrementalreader.structure.StructureViewModel

@Module
interface ViewModelModule {

    @Binds
    @AppScope
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(viewModelMain: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CreationViewModel::class)
    fun bindCreationViewModel(viewModel: CreationViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(StructureViewModel::class)
    fun bindStructureViewModel(viewModel: StructureViewModel): ViewModel

}
