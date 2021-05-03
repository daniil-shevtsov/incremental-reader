package shevtsov.daniil.incrementalreader.creation.presentation

data class CreationViewState(
    val title: String,
    val content: String,
    val contentItems: List<String> = emptyList(),
)