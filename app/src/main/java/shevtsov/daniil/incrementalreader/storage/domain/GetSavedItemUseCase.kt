package shevtsov.daniil.incrementalreader.storage.domain

import javax.inject.Inject

class GetSavedItemUseCase @Inject constructor(
    private val repository: TextStorageRepository
) {

    operator fun invoke(itemId: String) = repository.getItem(itemId = itemId)

}