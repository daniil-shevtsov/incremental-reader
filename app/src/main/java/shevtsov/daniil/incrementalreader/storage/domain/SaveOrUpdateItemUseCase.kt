package shevtsov.daniil.incrementalreader.storage.domain

import shevtsov.daniil.incrementalreader.storage.domain.model.InformationItem
import javax.inject.Inject

class SaveOrUpdateItemUseCase @Inject constructor(
    private val repository: TextStorageRepository
) {
    suspend operator fun invoke(
        id: Long?,
        name: String,
        content: String,
        parentId: Long?,
    ) {
        val item = id?.let { repository.getItem(id) }

        if (item != null) {
            repository.update(
                item.copy(
                    title = name,
                    content = content
                )
            )
        } else {
            repository.saveText(
                item = InformationItem(
                    id = id,
                    title = name,
                    content = content,
                    creationTime = System.currentTimeMillis(),
                    updateTime = 0L,
                    lastReviewTime = 0L,
                    parentId = parentId
                )
            )
        }
    }
}