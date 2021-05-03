package shevtsov.daniil.incrementalreader.core.storage.room.repetition

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import shevtsov.daniil.incrementalreader.core.storage.room.information.InformationItemEntity

@Entity(
    tableName = "repetition_table",
    foreignKeys = [
        ForeignKey(
            entity = InformationItemEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("information_item_id"),
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class RepetitionEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val repetitionId: Long = 0L,

    @ColumnInfo(name = "information_item_id") val informationItemId: Long,

    @ColumnInfo(name = "repetition_time") val repetitionTime: Long,

    @ColumnInfo(name = "score") val score: Long,
)