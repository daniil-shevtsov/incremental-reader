package shevtsov.daniil.incrementalreader.creation.di

import dagger.Binds
import dagger.Module
import shevtsov.daniil.incrementalreader.core.di.AppScope
import shevtsov.daniil.incrementalreader.storage.data.RepetitionRepositoryImpl
import shevtsov.daniil.incrementalreader.storage.data.TextStorageRepositoryImpl
import shevtsov.daniil.incrementalreader.storage.domain.RepetitionRepository
import shevtsov.daniil.incrementalreader.storage.domain.TextStorageRepository

@Module
interface CreationModule {

    @AppScope
    @Binds
    fun bindStorageRepository(impl: TextStorageRepositoryImpl): TextStorageRepository

    @AppScope
    @Binds
    fun bindRepetitionRepository(impl: RepetitionRepositoryImpl): RepetitionRepository

}