package shevtsov.daniil.incrementalreader.structure

import shevtsov.daniil.incrementalreader.storage.domain.InformationItem
import javax.inject.Inject

class StructureInformationItemMapper @Inject constructor() {

    fun map(informationItem: InformationItem) = with(informationItem) {
        StructureInformationItem(
            id = id,
            title = name
        )
    }

}