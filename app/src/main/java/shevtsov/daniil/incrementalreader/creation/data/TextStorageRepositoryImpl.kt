package shevtsov.daniil.incrementalreader.creation.data

import shevtsov.daniil.incrementalreader.creation.domain.TextStorageRepository
import javax.inject.Inject

class TextStorageRepositoryImpl @Inject constructor(

) : TextStorageRepository {

    private val items = mutableMapOf<String, String>()

    override fun saveText(itemName: String, text: String) {
        items[itemName] = text
    }

}