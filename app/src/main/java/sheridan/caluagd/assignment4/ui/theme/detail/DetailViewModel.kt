package sheridan.caluagd.assignment4.ui.theme.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import sheridan.caluagd.assignment4.database.MarsPhotosRepository
import sheridan.caluagd.assignment4.navigation.DetailPageNavigation
import javax.inject.Inject


@HiltViewModel
class ProductEditViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    navigateback: () -> Unit,
    photosRepository: MarsPhotosRepository
) : ViewModel() {

    private val productId: Int =
        checkNotNull(savedStateHandle[DetailPageNavigation.MARS_ID_ARG])

    val uiState: StateFlow<DetailUiState> =
        photosRepository.getPhotoById(productId)
            .filterNotNull()
            .map { item ->
                DetailUiState(item)
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = DetailUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}