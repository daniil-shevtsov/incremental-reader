package shevtsov.daniil.incrementalreader.creation.domain

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