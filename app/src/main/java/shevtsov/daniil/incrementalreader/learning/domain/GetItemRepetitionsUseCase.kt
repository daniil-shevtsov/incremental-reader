package shevtsov.daniil.incrementalreader.learning.domain

import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.single
import shevtsov.daniil.incrementalreader.storage.domain.RepetitionRepository
import shevtsov.daniil.incrementalreader.storage.domain.model.Repetition
import javax.inject.Inject

class GetItemRepetitionsUseCase @Inject constructor(
    private val repetitionRepository: RepetitionRepository
) {

    suspend operator fun invoke(itemId: Long): List<Repetition> = repetitionRepository.getItems()
        .map { items -> items.filter { it.informationItemId == itemId } }
        .single()

}