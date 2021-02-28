package shevtsov.daniil.incrementalreader.core.storage.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "information_item_table")
data class InformationItemEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val itemId: Long = 0L,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "text") val text: String
)