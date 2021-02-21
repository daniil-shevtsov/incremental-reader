package shevtsov.daniil.incrementalreader.core.di.storage

import dagger.Binds
import dagger.Module
import shevtsov.daniil.incrementalreader.core.di.AppScope
import shevtsov.daniil.incrementalreader.core.storage.StorageApi
import shevtsov.daniil.incrementalreader.core.storage.TextStorage

@Module
interface StorageModule {

    @Binds
    @AppScope
    fun bindStorage(impl: TextStorage): StorageApi

}