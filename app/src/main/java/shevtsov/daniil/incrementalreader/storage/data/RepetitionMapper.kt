package shevtsov.daniil.incrementalreader.storage.data

import shevtsov.daniil.incrementalreader.core.storage.room.repetition.RepetitionEntity
import shevtsov.daniil.incrementalreader.learning.domain.ScoreValue
import shevtsov.daniil.incrementalreader.storage.domain.model.Repetition
import javax.inject.Inject

class RepetitionMapper @Inject constructor() {

    fun map(
        repetition: Repetition
    ): RepetitionEntity = with(repetition) {
        RepetitionEntity(
            repetitionId = repetitionId ?: 0L,
            informationItemId = informationItemId,
            repetitionTime = repetitionTime,
            score = score.value,
        )
    }

    fun map(
        repetitionEntity: RepetitionEntity
    ): Repetition = with(repetitionEntity) {
        Repetition(
            repetitionId = repetitionId,
            informationItemId = informationItemId,
            repetitionTime = repetitionTime,
            score = ScoreValue(score),
        )
    }

}