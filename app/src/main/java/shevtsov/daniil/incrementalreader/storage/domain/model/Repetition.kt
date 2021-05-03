package shevtsov.daniil.incrementalreader.storage.domain.model

data class Repetition(
    val repetitionId: Long?,
    val informationItemId: Long,
    val repetitionTime: Long,
    val score: Long,
)