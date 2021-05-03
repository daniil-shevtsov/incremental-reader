package shevtsov.daniil.incrementalreader.core.storage

import kotlinx.coroutines.flow.Flow
import shevtsov.daniil.incrementalreader.core.storage.room.information.InformationItemDao
import shevtsov.daniil.incrementalreader.core.storage.room.information.InformationItemEntity
import shevtsov.daniil.incrementalreader.core.storage.room.repetition.RepetitionDao
import shevtsov.daniil.incrementalreader.core.storage.room.repetition.RepetitionEntity
import javax.inject.Inject

class DatabaseStorage @Inject constructor(
    private val informationItemDao: InformationItemDao,
    private val repetitionDao: RepetitionDao,
) {

    suspend fun save(value: InformationItemEntity) {
        informationItemDao.insert(value)
    }

    suspend fun update(value: InformationItemEntity) {
        informationItemDao.update(value)
    }

    suspend fun get(itemId: Long): InformationItemEntity? {
        return informationItemDao.getItem(id = itemId)
    }

    fun getAll(): Flow<List<InformationItemEntity>> {
        return informationItemDao.getAllItems()
    }

    suspend fun saveRepetition(repetitionEntity: RepetitionEntity) {
        repetitionDao.insert(entity = repetitionEntity)
    }

    fun getALlRepetitions(): Flow<List<RepetitionEntity>> {
        return repetitionDao.getAllItems()
    }

}