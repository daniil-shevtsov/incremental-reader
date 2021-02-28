package shevtsov.daniil.incrementalreader.storage.domain.model

data class InformationItem(
    val id: Long,
    val title: String,
    val content: String,
    val creationTime: Long,
    val updateTime: Long,
    val lastRepetitionTime: Long,
    val parentId: Long?,
)