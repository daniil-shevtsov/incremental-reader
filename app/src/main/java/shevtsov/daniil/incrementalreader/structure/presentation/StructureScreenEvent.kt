package shevtsov.daniil.incrementalreader.structure.presentation

sealed class StructureScreenEvent {
    data class OpenLearning(val itemId: Long) : StructureScreenEvent()
    data class OpenCreation(val itemId: Long) : StructureScreenEvent()
}