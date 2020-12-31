package shevtsov.daniil.incrementalreader.storage.domain

interface TextStorageRepository {

    fun saveText(itemName: String, text: String)

    fun getItems(): List<InformationItem>

}