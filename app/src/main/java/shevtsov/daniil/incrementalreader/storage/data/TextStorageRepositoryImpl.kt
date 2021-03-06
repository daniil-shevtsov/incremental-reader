package shevtsov.daniil.incrementalreader.storage.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import shevtsov.daniil.incrementalreader.core.storage.DatabaseStorage
import shevtsov.daniil.incrementalreader.storage.domain.TextStorageRepository
import shevtsov.daniil.incrementalreader.storage.domain.model.InformationItem
import javax.inject.Inject

class TextStorageRepositoryImpl @Inject constructor(
    private val databaseStorage: DatabaseStorage,
    private val mapper: InformationItemMapper
) : TextStorageRepository {

    override suspend fun saveText(item: InformationItem) {
        databaseStorage.save(value = mapper.map(item))
    }

    override suspend fun update(item: InformationItem) {
        databaseStorage.update(value = mapper.map(item))
    }

    override fun getItems(): Flow<List<InformationItem>> {
        return databaseStorage.getAll().map { items ->
            items.map { item ->
                mapper.map(item)
            }
        }
    }

    override suspend fun getItem(itemId: Long): InformationItem? {
        return databaseStorage.get(itemId = itemId)?.let { item -> mapper.map(item) }
    }

}