package shevtsov.daniil.incrementalreader.learning.domain

import shevtsov.daniil.incrementalreader.storage.domain.RepetitionRepository
import shevtsov.daniil.incrementalreader.storage.domain.model.InformationItem
import shevtsov.daniil.incrementalreader.storage.domain.model.Repetition
import javax.inject.Inject

class AddRepetitionUseCase @Inject constructor(
    private val repetitionRepository: RepetitionRepository
) {

    suspend operator fun invoke(
        item: InformationItem,
        score: Score
    ) {
        if (item.id != null) {
            val repetition = Repetition(
                repetitionId = null,
                informationItemId = item.id,
                repetitionTime = System.currentTimeMillis(),
                score = score.value,
            )
            repetitionRepository.saveText(repetition = repetition)
        }
    }

}