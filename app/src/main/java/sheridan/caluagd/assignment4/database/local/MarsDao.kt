package sheridan.caluagd.assignment4.database.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow


@Dao
interface MarsDao {

    @Query("SELECT * FROM mars")
    fun getLocalPhotos(): Flow<List<LocalMars>>

    @Query("SELECT * FROM mars WHERE r_id = :id")
    suspend fun getPhotoById(id: Int): LocalMars?

    @Upsert
    suspend fun upsertMars(restaurant: List<LocalMars>)

    @Query("DELETE FROM mars")
    suspend fun deleteList()

    @Transaction
    suspend fun refreshRestaurant(list: List<LocalMars>){
        deleteList()
        upsertMars(list)
    }
}