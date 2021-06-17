package shevtsov.daniil.incrementalreader.storage.domain.model

import shevtsov.daniil.incrementalreader.learning.domain.ScoreValue

data class Repetition(
    val repetitionId: Long?,
    val informationItemId: Long,
    val repetitionTime: Long,
    val score: ScoreValue,
)