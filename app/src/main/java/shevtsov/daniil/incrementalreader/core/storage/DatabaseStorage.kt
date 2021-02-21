package shevtsov.daniil.incrementalreader.core.storage

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import shevtsov.daniil.incrementalreader.core.storage.room.InformationItemDao
import shevtsov.daniil.incrementalreader.core.storage.room.InformationItemEntity
import shevtsov.daniil.incrementalreader.storage.data.InformationItemDto
import javax.inject.Inject

class DatabaseStorage @Inject constructor(
    private val informationItemDao: InformationItemDao
) : StorageApi {

    override suspend fun save(key: String, value: InformationItemDto) {
        informationItemDao.insert(value.toEntity())
    }

    override suspend fun get(key: String): InformationItemDto? {
        return informationItemDao.getItem(id = key.toLong())?.toDto()
    }

    override fun getAll(): Flow<List<InformationItemDto>> {
        return informationItemDao.getAllItems().map { items -> items.map { item -> item.toDto() } }
    }

    private fun InformationItemDto.toEntity() = InformationItemEntity(
        itemId = id.toLong(),
        title = name,
        text = content
    )

    private fun InformationItemEntity.toDto() = InformationItemDto(
        id = itemId.toString(),
        name = title,
        content = text
    )

}