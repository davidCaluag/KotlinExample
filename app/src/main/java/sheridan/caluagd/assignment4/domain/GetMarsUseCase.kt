package sheridan.caluagd.assignment4.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import sheridan.caluagd.assignment4.model.Mars
import sheridan.caluagd.assignment4.repository.OfflineRepository
import javax.inject.Inject

class GetMarsUseCase @Inject constructor(private val repository: OfflineRepository) {
    operator fun invoke(): Flow<List<Mars>> = repository.getLocalPhotos().map{it}
}