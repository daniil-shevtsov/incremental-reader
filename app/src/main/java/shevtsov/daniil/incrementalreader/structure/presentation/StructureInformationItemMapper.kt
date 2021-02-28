package shevtsov.daniil.incrementalreader.structure.presentation

import shevtsov.daniil.incrementalreader.storage.domain.model.InformationItem
import shevtsov.daniil.incrementalreader.structure.view.adapter.StructureInformationItem
import javax.inject.Inject

class StructureInformationItemMapper @Inject constructor() {

    fun map(informationItem: InformationItem): StructureInformationItem = with(informationItem) {
        StructureInformationItem(
            id = id,
            title = title
        )
    }

}