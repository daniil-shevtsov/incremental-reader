package shevtsov.daniil.incrementalreader.structure

sealed class StructureScreenEvent {
    data class OpenLearning(val itemId: String) : StructureScreenEvent()
}