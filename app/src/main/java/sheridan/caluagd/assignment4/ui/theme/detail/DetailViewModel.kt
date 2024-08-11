package sheridan.caluagd.assignment4.ui.theme.detail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import sheridan.caluagd.assignment4.model.Mars
import sheridan.caluagd.assignment4.navigation.DetailPageNavigation
import sheridan.caluagd.assignment4.repository.OfflineRepository
import javax.inject.Inject


@HiltViewModel
class MarsDetailViewModel @Inject constructor(
    photosRepository: OfflineRepository,
    stateHandle: SavedStateHandle
) : ViewModel() {

    private val _detailsUiState: MutableStateFlow<DetailsUiState> =
        MutableStateFlow(DetailsUiState.Loading)
    val detailsUiState: StateFlow<DetailsUiState> = _detailsUiState



    init{
        viewModelScope.launch {
            val productId: Int = stateHandle.get<Int>(DetailPageNavigation.MARS_ID_ARG)!!
            val _mars : Mars? = photosRepository.getPhotoById(productId)
            if(_mars != null){
                _detailsUiState.update {
                    DetailsUiState.Success(mars = _mars)
                }
            }else{
                Log.e("DetailsViewModel", "data for id=$productId is not found")
            }
        }
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}