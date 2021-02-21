package shevtsov.daniil.incrementalreader.core.storage

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import shevtsov.daniil.incrementalreader.core.di.AppScope
import shevtsov.daniil.incrementalreader.storage.data.InformationItemDto
import javax.inject.Inject

@AppScope
class TextStorage @Inject constructor(

) : StorageApi {
    private val items = mutableMapOf<String, InformationItemDto>()

    override suspend fun save(key: String, value: InformationItemDto) {
        items[key] = value
    }

    override suspend fun get(key: String): InformationItemDto? = items[key]

    override fun getAll(): Flow<List<InformationItemDto>> = flowOf(items.values.toList())

}