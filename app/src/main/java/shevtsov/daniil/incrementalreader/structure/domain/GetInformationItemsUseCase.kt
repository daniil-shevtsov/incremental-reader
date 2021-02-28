package shevtsov.daniil.incrementalreader.structure.domain

import kotlinx.coroutines.flow.Flow
import shevtsov.daniil.incrementalreader.storage.domain.TextStorageRepository
import shevtsov.daniil.incrementalreader.storage.domain.model.InformationItem
import javax.inject.Inject

class GetInformationItemsUseCase @Inject constructor(
    private val textStorageRepository: TextStorageRepository
) {

    operator fun invoke(): Flow<List<InformationItem>> = textStorageRepository.getItems()

}