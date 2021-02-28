package shevtsov.daniil.incrementalreader.structure.presentation

import shevtsov.daniil.incrementalreader.structure.view.adapter.StructureInformationItem

sealed class StructureContentViewState {
    object Loading : StructureContentViewState()
    data class Content(val items: List<StructureInformationItem>) : StructureContentViewState()
}