package shevtsov.daniil.incrementalreader.storage.domain

import javax.inject.Inject

class SaveOrUpdateItemUseCase @Inject constructor(
    private val saveCreated: SaveCreatedUseCase,
    private val repository: TextStorageRepository
) {
    suspend operator fun invoke(
        name: String,
        content: String,
        id: Long? = null,
    ) {
        try {
            saveCreated(name, content)
        } catch (e: Exception) {
            if(id != null) {
                val item = repository.getItem(id)
                if(item != null) {
                    repository.update(item.copy(title = name, content = content))
                }
            }
        }
    }
}