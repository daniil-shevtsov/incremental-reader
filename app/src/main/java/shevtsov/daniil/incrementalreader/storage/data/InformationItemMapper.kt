package shevtsov.daniil.incrementalreader.storage.data

import shevtsov.daniil.incrementalreader.storage.domain.InformationItem
import javax.inject.Inject

class InformationItemMapper @Inject constructor() {
    fun map(dto: InformationItemDto): InformationItem = with(dto) {
        InformationItem(
            id = id,
            name = name,
            content = content
        )
    }
}