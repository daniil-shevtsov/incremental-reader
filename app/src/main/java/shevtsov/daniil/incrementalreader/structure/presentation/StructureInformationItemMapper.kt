package shevtsov.daniil.incrementalreader.structure.presentation

import shevtsov.daniil.incrementalreader.storage.domain.InformationItem
import shevtsov.daniil.incrementalreader.structure.view.adapter.StructureInformationItem
import javax.inject.Inject

class StructureInformationItemMapper @Inject constructor() {

    fun map(informationItem: InformationItem) = with(informationItem) {
        StructureInformationItem(
            id = id,
            title = name
        )
    }

}