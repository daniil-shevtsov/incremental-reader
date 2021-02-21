package shevtsov.daniil.incrementalreader.storage.domain

import javax.inject.Inject

class SaveCreatedUseCase @Inject constructor(
    private val textStorageRepository: TextStorageRepository
) {

    operator fun invoke(
        itemName: String,
        text: String
    ) {
        textStorageRepository.saveText(
            itemName = itemName,
            text = text
        )
    }

}