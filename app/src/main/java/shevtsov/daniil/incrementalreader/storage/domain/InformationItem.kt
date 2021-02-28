package shevtsov.daniil.incrementalreader.storage.domain

data class InformationItem(
    val id: Long,
    val name: String,
    val content: String
)