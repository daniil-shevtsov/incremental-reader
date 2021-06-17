package shevtsov.daniil.incrementalreader.learning.domain

data class ItemScores(
    val itemId: Long,
    val scores: List<ScoreValue>
)