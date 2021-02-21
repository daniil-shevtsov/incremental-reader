package shevtsov.daniil.incrementalreader.structure.domain

import shevtsov.daniil.incrementalreader.storage.domain.TextStorageRepository
import javax.inject.Inject

class GetInformationItemsUseCase @Inject constructor(
    private val textStorageRepository: TextStorageRepository
) {

    operator fun invoke() = textStorageRepository.getItems()

}