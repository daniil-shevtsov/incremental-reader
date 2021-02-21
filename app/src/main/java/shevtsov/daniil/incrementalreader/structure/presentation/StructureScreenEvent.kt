package shevtsov.daniil.incrementalreader.structure.presentation

sealed class StructureScreenEvent {
    data class OpenLearning(val itemId: String) : StructureScreenEvent()
}