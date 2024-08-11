package sheridan.caluagd.assignment4.domain

import sheridan.caluagd.assignment4.repository.OfflineRepository
import javax.inject.Inject

class ReloadUseCase @Inject constructor(private val repository: OfflineRepository){
    suspend operator fun invoke() = repository.updateLocalPhotos()
}

class DeleteUseCase @Inject constructor(private val repository: OfflineRepository){
    suspend operator fun invoke() = repository.deleteLocalPhotos()
}