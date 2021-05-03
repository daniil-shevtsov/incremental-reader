package shevtsov.daniil.incrementalreader.core.storage.room.repetition

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RepetitionDao {

    @Query("SELECT * FROM repetition_table ORDER BY id ASC")
    fun getAllItems(): Flow<List<RepetitionEntity>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(entity: RepetitionEntity): Long

}