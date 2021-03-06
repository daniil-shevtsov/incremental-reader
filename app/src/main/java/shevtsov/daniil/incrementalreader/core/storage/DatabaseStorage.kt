package shevtsov.daniil.incrementalreader.core.storage

import android.util.Log
import kotlinx.coroutines.flow.Flow
import shevtsov.daniil.incrementalreader.core.storage.room.InformationItemDao
import shevtsov.daniil.incrementalreader.core.storage.room.InformationItemEntity
import javax.inject.Inject

class DatabaseStorage @Inject constructor(
    private val informationItemDao: InformationItemDao
) {

    suspend fun save(value: InformationItemEntity) {
        val insertedId = informationItemDao.insert(value)
        Log.d(
            "BD_DEBUG",
            "DatabaseStorage save: ${value.title} with id ${value.itemId} saved by id $insertedId"
        )
    }

    suspend fun update(value: InformationItemEntity) {
        val insertedId = informationItemDao.update(value)
        Log.d(
            "BD_DEBUG",
            "DatabaseStorage update: ${value.title} with id ${value.itemId} updated by id $insertedId"
        )
    }

    suspend fun get(itemId: Long): InformationItemEntity? {
        val item = informationItemDao.getItem(id = itemId)
        Log.d(
            "BD_DEBUG",
            "DatabaseStorage get: ${item?.title} with id ${item?.itemId} by id $itemId"
        )
        return item
    }

    fun getAll(): Flow<List<InformationItemEntity>> {
        return informationItemDao.getAllItems()
    }

}