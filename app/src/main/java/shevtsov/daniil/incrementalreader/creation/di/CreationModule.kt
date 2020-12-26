package shevtsov.daniil.incrementalreader.creation.di

import dagger.Binds
import dagger.Module
import shevtsov.daniil.incrementalreader.core.di.AppScope
import shevtsov.daniil.incrementalreader.creation.data.TextStorageRepositoryImpl
import shevtsov.daniil.incrementalreader.creation.domain.TextStorageRepository

@Module
interface CreationModule {

    @AppScope
    @Binds
    fun bindStorageRepository(impl: TextStorageRepositoryImpl): TextStorageRepository

}