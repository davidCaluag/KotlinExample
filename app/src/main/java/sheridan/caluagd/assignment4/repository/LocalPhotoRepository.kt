package sheridan.caluagd.assignment4.repository

import kotlinx.coroutines.flow.Flow
import sheridan.caluagd.assignment4.model.Mars

interface OfflineRepository{
    fun getLocalPhotos() : Flow<List<Mars>>

    suspend fun getPhotoById(int: Int): Mars?

    suspend fun updateLocalPhotos()

    suspend fun deleteLocalPhotos()
}