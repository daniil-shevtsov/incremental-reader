package shevtsov.daniil.incrementalreader.storage.domain

import kotlinx.coroutines.flow.Flow

interface TextStorageRepository {

   suspend fun saveText(itemName: String, text: String)

   fun getItems(): Flow<List<InformationItem>>

   suspend fun getItem(itemId: String): InformationItem?

}