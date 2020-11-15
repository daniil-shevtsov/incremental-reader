package shevtsov.daniil.incrementalreader.core.di

import dagger.Module
import dagger.Provides
import shevtsov.daniil.incrementalreader.core.di.api.network.NetworkModule
import shevtsov.daniil.incrementalreader.core.di.viewmodel.ViewModelModule
import shevtsov.daniil.incrementalreader.core.util.logging.AndroidLogger
import shevtsov.daniil.incrementalreader.core.util.logging.Logger

@Module(
    includes = [
        ViewModelModule::class,
        NetworkModule::class
    ]
)
class AppModule {

    @Provides
    fun provideLogger(): Logger = AndroidLogger()

}
