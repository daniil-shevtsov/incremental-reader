package shevtsov.daniil.incrementalreader.storage.domain

import shevtsov.daniil.incrementalreader.storage.domain.model.InformationItem
import javax.inject.Inject

class SaveCreatedUseCase @Inject constructor(
    private val textStorageRepository: TextStorageRepository
) {

    suspend operator fun invoke(
        itemName: String,
        text: String,
        id: Long? = null
    ) {
        val item = InformationItem(
            id = id,
            title = itemName,
            content = text,
            creationTime = System.currentTimeMillis(),
            updateTime = 0L,
            lastReviewTime = 0L,
            parentId = null
        )
        textStorageRepository.saveText(item)
    }

}