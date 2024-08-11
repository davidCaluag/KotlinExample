package sheridan.caluagd.assignment4.ui.theme.home


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import sheridan.caluagd.assignment4.domain.DeleteUseCase
import sheridan.caluagd.assignment4.domain.GetMarsUseCase
import sheridan.caluagd.assignment4.domain.ReloadUseCase
import sheridan.caluagd.assignment4.model.Mars
import sheridan.caluagd.assignment4.database.MarsPhoto
import javax.inject.Inject


/**
 * UI state for the Home screen
 */
sealed interface MarsUiState {
    data class Success(val photos: List<Mars>) : MarsUiState
    object Error : MarsUiState
    object Loading : MarsUiState
}


@HiltViewModel
class MarsViewModel @Inject constructor(
    getMarsUseCase: GetMarsUseCase,
    private val reloadUseCase: ReloadUseCase,
    private val deleteUseCase: DeleteUseCase): ViewModel()
{
    /** The mutable State that stores the status of the most recent request */
    val marsUiState: StateFlow<MarsUiState> = getMarsUseCase().map{
        MarsUiState.Success(it)
        }
        .catch{errorHandler}
        .stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
        initialValue = MarsUiState.Loading
    )

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        exception.printStackTrace()
    }


    fun reload() = viewModelScope.launch(errorHandler){
        reloadUseCase()
    }

    fun delete() = viewModelScope.launch(errorHandler){
        deleteUseCase()
    }

    companion object{
        const val TIMEOUT_MILLIS = 5_000L
    }



    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
//    init {
//        getMarsUseCase()
//    }

    /**
     * Gets Mars photos information from the Mars API Retrofit service and updates the
     * [MarsPhoto] [List] [MutableList].
     */
//    fun getMarsPhotos() {
//        viewModelScope.launch {
//            marsUiState = MarsUiState.Loading
//            marsUiState = try {
//                MarsUiState.Success(marsPhotosRepository.getMarsPhotos())
//            } catch (e: IOException) {
//                MarsUiState.Error
//            } catch (e: HttpException) {
//                MarsUiState.Error
//            }
//        }
//    }
//
//    /**
//     * Factory for [MarsViewModel] that takes [MarsPhotosRepository] as a dependency
//     */
//    companion object {
//        val Factory: ViewModelProvider.Factory = viewModelFactory {
//            initializer {
//                val application = (this[APPLICATION_KEY] as MarsPhotoApplication)
//                val marsPhotosRepository = application.container.marsPhotosRepository
//                MarsViewModel(marsPhotosRepository = marsPhotosRepository)
//            }
//        }
//    }
}
