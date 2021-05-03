package shevtsov.daniil.incrementalreader.storage.domain

import kotlinx.coroutines.flow.Flow
import shevtsov.daniil.incrementalreader.storage.domain.model.Repetition

interface RepetitionRepository {

    suspend fun saveText(repetition: Repetition)

    fun getItems(): Flow<List<Repetition>>

}