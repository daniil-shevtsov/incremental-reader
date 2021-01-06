package shevtsov.daniil.incrementalreader.structure.adapter

sealed class StructureAdapterAction {
    data class ItemSelected(val itemId: String) : StructureAdapterAction()
}