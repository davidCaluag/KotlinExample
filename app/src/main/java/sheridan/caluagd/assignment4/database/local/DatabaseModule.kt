package sheridan.caluagd.assignment4.database.local

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import sheridan.caluagd.assignment4.database.MarsDatabase
import sheridan.caluagd.assignment4.model.Mars
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideMarsDatabase(
        @ApplicationContext applicationContext: Context
    ): MarsDatabase = Room.databaseBuilder(
        applicationContext, MarsDatabase::class.java, "mars_database"
    ).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideMarsDao(
        database: MarsDatabase
    ): MarsDao = database.marsDao

}