package shevtsov.daniil.incrementalreader.core.storage

import kotlinx.coroutines.flow.Flow
import shevtsov.daniil.incrementalreader.core.storage.room.InformationItemDao
import shevtsov.daniil.incrementalreader.core.storage.room.InformationItemEntity
import javax.inject.Inject

class DatabaseStorage @Inject constructor(
    private val informationItemDao: InformationItemDao
) {

    suspend fun save(value: InformationItemEntity) {
        informationItemDao.insert(value)
    }

    suspend fun update(value: InformationItemEntity) {
        informationItemDao.update(value)
    }

    suspend fun get(itemId: Long): InformationItemEntity? {
        val item = informationItemDao.getItem(id = itemId)

        return item
    }

    fun getAll(): Flow<List<InformationItemEntity>> {
        return informationItemDao.getAllItems()
    }

}