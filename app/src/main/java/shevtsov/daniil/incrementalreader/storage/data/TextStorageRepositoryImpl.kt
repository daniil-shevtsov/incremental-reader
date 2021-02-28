package shevtsov.daniil.incrementalreader.storage.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import shevtsov.daniil.incrementalreader.core.storage.StorageApi
import shevtsov.daniil.incrementalreader.storage.domain.TextStorageRepository
import shevtsov.daniil.incrementalreader.storage.domain.model.InformationItem
import javax.inject.Inject

class TextStorageRepositoryImpl @Inject constructor(
    private val storageApi: StorageApi,
    private val mapper: InformationItemMapper
) : TextStorageRepository {

    override suspend fun saveText(item: InformationItem) {
        storageApi.save(value = mapper.map(item))
    }

    override fun getItems(): Flow<List<InformationItem>> {
        return storageApi.getAll().map { items ->
            items.map { item ->
                mapper.map(item)
            }
        }
    }

    override suspend fun getItem(itemId: Long): InformationItem? {
        return storageApi.get(itemId = itemId)?.let { item -> mapper.map(item) }
    }

}