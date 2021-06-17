package shevtsov.daniil.incrementalreader.core.storage.room

import androidx.room.Database
import androidx.room.RoomDatabase
import shevtsov.daniil.incrementalreader.core.storage.room.information.InformationItemDao
import shevtsov.daniil.incrementalreader.core.storage.room.information.InformationItemEntity
import shevtsov.daniil.incrementalreader.core.storage.room.repetition.RepetitionDao
import shevtsov.daniil.incrementalreader.core.storage.room.repetition.RepetitionEntity

@Database(
    entities = [InformationItemEntity::class, RepetitionEntity::class],
    version = 4,
    exportSchema = false
)
abstract class InformationItemDatabase : RoomDatabase() {
    abstract val informationItemDao: InformationItemDao
    abstract val repetitionDao: RepetitionDao
}