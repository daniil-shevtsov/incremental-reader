package shevtsov.daniil.incrementalreader.core.storage.room.information

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface InformationItemDao {

    @Query("SELECT * FROM information_item_table ORDER BY id ASC")
    fun getAllItems(): Flow<List<InformationItemEntity>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(entity: InformationItemEntity): Long

    @Update
    suspend fun update(entity: InformationItemEntity)

    @Query("SELECT * FROM information_item_table WHERE id = :id")
    suspend fun getItem(id: Long): InformationItemEntity?

}