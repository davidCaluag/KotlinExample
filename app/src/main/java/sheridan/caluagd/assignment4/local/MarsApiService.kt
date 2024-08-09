package sheridan.caluagd.assignment4.local


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import sheridan.caluagd.assignment4.model.MarsPhoto

@Dao
interface MarsApiService {
    /**
     * Returns a [List] of [MarsPhoto] and this method can be called from a Coroutine.
     * The @GET annotation indicates that the "photos" endpoint will be requested with the GET
     * HTTP method
     */
    @GET("photos")
    suspend fun getPhotos(): List<MarsPhoto>

    @Query("SELECT * FROM mars WHERE id = :id")
    fun getPhotoById(id: Int): Flow<MarsPhoto>

}
