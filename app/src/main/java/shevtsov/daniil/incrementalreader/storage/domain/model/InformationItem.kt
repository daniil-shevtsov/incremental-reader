package shevtsov.daniil.incrementalreader.storage.domain.model

data class InformationItem(
    val id: Long?,
    val title: String,
    val content: String,
    val creationTime: Long,
    val updateTime: Long,
    val lastReviewTime: Long,
    val parentId: Long?,
)