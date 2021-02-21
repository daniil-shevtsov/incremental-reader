package shevtsov.daniil.incrementalreader.core.storage.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [InformationItemEntity::class],
    version = 1,
    exportSchema = false
)
abstract class InformationItemDatabase : RoomDatabase() {
    abstract val informationItemDao: InformationItemDao
}