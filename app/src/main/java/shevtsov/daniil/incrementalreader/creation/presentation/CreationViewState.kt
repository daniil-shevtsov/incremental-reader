package shevtsov.daniil.incrementalreader.creation.presentation

data class CreationViewState(
    val title: String,
    val contentItems: List<Pair<Long, String>> = emptyList(),
)