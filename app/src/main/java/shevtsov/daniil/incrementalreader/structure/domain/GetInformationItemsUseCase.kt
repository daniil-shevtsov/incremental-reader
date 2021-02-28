package shevtsov.daniil.incrementalreader.structure.domain

import kotlinx.coroutines.flow.Flow
import shevtsov.daniil.incrementalreader.storage.domain.InformationItem
import shevtsov.daniil.incrementalreader.storage.domain.TextStorageRepository
import javax.inject.Inject

class GetInformationItemsUseCase @Inject constructor(
    private val textStorageRepository: TextStorageRepository
) {

    operator fun invoke(): Flow<List<InformationItem>> = textStorageRepository.getItems()

}