package shevtsov.daniil.incrementalreader.core.di.storage

import dagger.Binds
import dagger.Module
import shevtsov.daniil.incrementalreader.core.di.AppScope
import shevtsov.daniil.incrementalreader.core.storage.DatabaseStorage
import shevtsov.daniil.incrementalreader.core.storage.StorageApi

@Module
interface StorageModule {

    @Binds
    @AppScope
    fun bindStorage(impl: DatabaseStorage): StorageApi

}