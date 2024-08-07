package sheridan.caluagd.assignment4.local

import retrofit2.http.GET
import sheridan.caluagd.assignment4.model.MarsPhoto

interface MarsApiService {
    /**
     * Returns a [List] of [MarsPhoto] and this method can be called from a Coroutine.
     * The @GET annotation indicates that the "photos" endpoint will be requested with the GET
     * HTTP method
     */
    @GET("photos")
    suspend fun getPhotos(): List<MarsPhoto>
}
