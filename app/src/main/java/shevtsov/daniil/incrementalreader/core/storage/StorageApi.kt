package shevtsov.daniil.incrementalreader.core.storage

import kotlinx.coroutines.flow.Flow
import shevtsov.daniil.incrementalreader.storage.data.InformationItemDto

interface StorageApi {

    suspend fun save(key: String, value: InformationItemDto)

    suspend fun get(key: String): InformationItemDto?

    fun getAll(): Flow<List<InformationItemDto>>

}