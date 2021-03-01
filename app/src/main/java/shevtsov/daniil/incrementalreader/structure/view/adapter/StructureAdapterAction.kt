package shevtsov.daniil.incrementalreader.structure.view.adapter

sealed class StructureAdapterAction {
    data class ItemSelected(val itemId: Long) : StructureAdapterAction()
    data class  LongTap(val itemId: Long) : StructureAdapterAction()
}