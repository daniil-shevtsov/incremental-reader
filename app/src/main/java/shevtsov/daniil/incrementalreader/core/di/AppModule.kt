package shevtsov.daniil.incrementalreader.core.di

import dagger.Module
import dagger.Provides
import shevtsov.daniil.incrementalreader.core.di.storage.DatabaseModule
import shevtsov.daniil.incrementalreader.core.di.storage.StorageModule
import shevtsov.daniil.incrementalreader.core.di.viewmodel.ViewModelModule
import shevtsov.daniil.incrementalreader.core.util.logging.AndroidLogger
import shevtsov.daniil.incrementalreader.core.util.logging.Logger
import shevtsov.daniil.incrementalreader.creation.di.CreationModule

@Module(
    includes = [
        ViewModelModule::class,
        CreationModule::class,
        StorageModule::class,
        DatabaseModule::class
    ]
)
class AppModule {

    @Provides
    fun provideLogger(): Logger = AndroidLogger()

}
