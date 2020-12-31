package shevtsov.daniil.incrementalreader.storage.data

import shevtsov.daniil.incrementalreader.storage.domain.InformationItem
import shevtsov.daniil.incrementalreader.storage.domain.TextStorageRepository
import javax.inject.Inject

class TextStorageRepositoryImpl @Inject constructor(
    private val storage: TextStorage,
    private val mapper: InformationItemMapper
) : TextStorageRepository {


    override fun saveText(itemName: String, text: String) {
        storage.save(
            key = itemName,
            value = InformationItemDto(id = itemName, name = itemName, content = text)
        )
    }

    override fun getItems(): List<InformationItem> = storage.getAll().map(mapper::map)

}