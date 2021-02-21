package shevtsov.daniil.incrementalreader.core.storage.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface InformationItemDao {

    @Query("SELECT * FROM information_item_table ORDER BY id ASC")
    fun getAllItems(): Flow<List<InformationItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: InformationItemEntity)

    @Query("SELECT * FROM information_item_table WHERE id = :id")
    suspend fun getItem(id: Long): InformationItemEntity?

}