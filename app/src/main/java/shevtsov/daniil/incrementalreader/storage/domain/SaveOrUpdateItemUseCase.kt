package shevtsov.daniil.incrementalreader.storage.domain

import android.util.Log
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
            Log.d(
                "BD_DEBUG",
                "SaveOrUpdateItemUseCase try to dave ${name} by id $id"
            )
            saveCreated(name, content, id)
        } catch (e: Exception) {
            Log.d(
                "BD_DEBUG",
                "SaveOrUpdateItemUseCase could not save ${name}, try to update"
            )
            if(id != null) {
                val item = repository.getItem(id)
                if(item != null) {
                    repository.update(item.copy(title = name, content = content))
                }
            }
        }
    }
}