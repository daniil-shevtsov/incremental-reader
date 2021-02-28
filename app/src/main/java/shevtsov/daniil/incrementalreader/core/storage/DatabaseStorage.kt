package shevtsov.daniil.incrementalreader.core.storage

import kotlinx.coroutines.flow.Flow
import shevtsov.daniil.incrementalreader.core.storage.room.InformationItemDao
import shevtsov.daniil.incrementalreader.core.storage.room.InformationItemEntity
import javax.inject.Inject

class DatabaseStorage @Inject constructor(
    private val informationItemDao: InformationItemDao
) : StorageApi {

    override suspend fun save(value: InformationItemEntity) {
        informationItemDao.insert(value)
    }

    override suspend fun get(itemId: Long): InformationItemEntity? {
        val item = informationItemDao.getItem(id = itemId)

        return item
    }

    override fun getAll(): Flow<List<InformationItemEntity>> {
        return informationItemDao.getAllItems()
    }

}