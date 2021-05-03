package shevtsov.daniil.incrementalreader.core.di.storage

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import shevtsov.daniil.incrementalreader.core.di.AppScope
import shevtsov.daniil.incrementalreader.core.storage.room.InformationItemDatabase

@Module
class DatabaseModule {

    @Provides
    @AppScope
    fun provideRoomDatabase(
        context: Context
    ): InformationItemDatabase = Room.databaseBuilder(
        context.applicationContext,
        InformationItemDatabase::class.java,
        "information_item_database"
    ).fallbackToDestructiveMigration().build()

    @Provides
    @AppScope
    fun provideInformationItemDao(
        database: InformationItemDatabase
    ) = database.informationItemDao

    @Provides
    @AppScope
    fun provideRepetitionDao(
        database: InformationItemDatabase
    ) = database.repetitionDao

}