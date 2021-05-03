package shevtsov.daniil.incrementalreader.storage.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import shevtsov.daniil.incrementalreader.core.storage.DatabaseStorage
import shevtsov.daniil.incrementalreader.storage.domain.RepetitionRepository
import shevtsov.daniil.incrementalreader.storage.domain.model.Repetition
import javax.inject.Inject

class RepetitionRepositoryImpl @Inject constructor(
    private val databaseStorage: DatabaseStorage,
    private val repetitionMapper: RepetitionMapper,
) : RepetitionRepository {

    override suspend fun saveText(repetition: Repetition) {
        databaseStorage.saveRepetition(repetitionEntity = repetitionMapper.map(repetition))
    }

    override fun getItems(): Flow<List<Repetition>> {
        return databaseStorage.getALlRepetitions()
            .map { entityList ->
                entityList.map(repetitionMapper::map)
            }
    }
}