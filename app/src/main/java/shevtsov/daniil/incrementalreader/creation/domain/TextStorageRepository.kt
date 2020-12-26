package shevtsov.daniil.incrementalreader.creation.domain

interface TextStorageRepository {
    fun saveText(itemName: String, text: String)
}