package shevtsov.daniil.incrementalreader.core.storage

import kotlinx.coroutines.flow.Flow
import shevtsov.daniil.incrementalreader.storage.data.InformationItemDto

interface StorageApi {

    suspend fun save(value: InformationItemDto)

    suspend fun get(itemId: Long): InformationItemDto?

    fun getAll(): Flow<List<InformationItemDto>>

}