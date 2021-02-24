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

    override suspend fun save(value: InformationItemDto) {
        informationItemDao.insert(value.toEntity())
    }

    override suspend fun get(itemId: Long): InformationItemDto? {
        return informationItemDao.getItem(id = itemId)?.toDto()
    }

    override fun getAll(): Flow<List<InformationItemDto>> {
        return informationItemDao.getAllItems().map { items -> items.map { item -> item.toDto() } }
    }

    private fun InformationItemDto.toEntity() = InformationItemEntity(
        title = name,
        text = content
    )

    private fun InformationItemEntity.toDto() = InformationItemDto(
        id = itemId,
        name = title,
        content = text
    )

}