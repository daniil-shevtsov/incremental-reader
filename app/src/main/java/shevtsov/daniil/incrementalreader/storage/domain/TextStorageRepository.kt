package shevtsov.daniil.incrementalreader.storage.domain

import kotlinx.coroutines.flow.Flow
import shevtsov.daniil.incrementalreader.storage.domain.model.InformationItem

interface TextStorageRepository {

   suspend fun saveText(item: InformationItem)

   suspend fun update(item: InformationItem)

   fun getItems(): Flow<List<InformationItem>>

   suspend fun getItem(itemId: Long): InformationItem?

}