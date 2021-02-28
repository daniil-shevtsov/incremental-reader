package shevtsov.daniil.incrementalreader.storage.domain

import javax.inject.Inject

class GetSavedItemUseCase @Inject constructor(
    private val repository: TextStorageRepository
) {

    suspend operator fun invoke(itemId: Long) = repository.getItem(itemId = itemId)

}