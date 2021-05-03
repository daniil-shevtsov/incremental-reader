package shevtsov.daniil.incrementalreader.storage.data

import shevtsov.daniil.incrementalreader.core.storage.room.information.InformationItemEntity
import shevtsov.daniil.incrementalreader.storage.domain.model.InformationItem
import javax.inject.Inject

class InformationItemMapper @Inject constructor() {
    fun map(entity: InformationItemEntity): InformationItem = with(entity) {
        InformationItem(
            id = itemId,
            title = title,
            content = content,
            creationTime = creationTime,
            updateTime = updateTime,
            lastReviewTime = lastReviewTime,
            parentId = parentId,
        )
    }

    fun map(item: InformationItem): InformationItemEntity = with(item) {
        InformationItemEntity(
            itemId = id ?: 0L,
            title = title,
            content = content,
            creationTime = creationTime,
            updateTime = updateTime,
            lastReviewTime = lastReviewTime,
            parentId = parentId,
        )
    }
}