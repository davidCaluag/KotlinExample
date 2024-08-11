package sheridan.caluagd.assignment4.database

import androidx.room.Database
import androidx.room.RoomDatabase
import sheridan.caluagd.assignment4.database.local.LocalMars
import sheridan.caluagd.assignment4.database.local.MarsDao


@Database(
    entities = [LocalMars::class],version = 2, exportSchema = false
)
abstract class MarsDatabase : RoomDatabase() {
    abstract val marsDao: MarsDao
}