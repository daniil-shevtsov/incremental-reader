package shevtsov.daniil.incrementalreader.core.storage

import kotlinx.coroutines.flow.Flow
import shevtsov.daniil.incrementalreader.core.storage.room.InformationItemEntity

interface StorageApi {

    suspend fun save(value: InformationItemEntity)

    suspend fun get(itemId: Long): InformationItemEntity?

    fun getAll(): Flow<List<InformationItemEntity>>

}