package sheridan.caluagd.assignment4.database

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.flow.Flow
import sheridan.caluagd.assignment4.local.MarsApiService
import sheridan.caluagd.assignment4.model.MarsPhoto


@OptIn(DelicateCoroutinesApi::class)
interface MarsPhotosRepository {
    /** Fetches list of MarsPhoto from marsApi */
    suspend fun getMarsPhotos(): List<MarsPhoto>

    fun getPhotoById(int: Int): Flow<MarsPhoto>
}

/**
 * Network Implementation of Repository that fetch mars photos list from marsApi.
 */
class NetworkMarsPhotosRepository(
    private val marsApiService: MarsApiService
) : MarsPhotosRepository {
    /** Fetches list of MarsPhoto from marsApi*/
    override suspend fun getMarsPhotos(): List<MarsPhoto> = marsApiService.getPhotos()
    override fun getPhotoById(int: Int): Flow<MarsPhoto> = marsApiService.getPhotoById(int)
}
