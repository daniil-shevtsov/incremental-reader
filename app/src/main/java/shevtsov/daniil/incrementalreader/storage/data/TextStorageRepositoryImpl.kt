package shevtsov.daniil.incrementalreader.storage.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import shevtsov.daniil.incrementalreader.core.storage.StorageApi
import shevtsov.daniil.incrementalreader.storage.domain.InformationItem
import shevtsov.daniil.incrementalreader.storage.domain.TextStorageRepository
import javax.inject.Inject

class TextStorageRepositoryImpl @Inject constructor(
    private val storageApi: StorageApi,
    private val mapper: InformationItemMapper
) : TextStorageRepository {

    override suspend fun saveText(itemName: String, text: String) {
        storageApi.save(value = InformationItemDto(name = itemName, content = text))
    }

    override fun getItems(): Flow<List<InformationItem>> {
        return storageApi.getAll().map { items -> items.map(mapper::map) }
    }

    override suspend fun getItem(itemId: Long): InformationItem? {
        return storageApi.get(itemId = itemId)?.let { mapper.map(dto = it) }
    }

}