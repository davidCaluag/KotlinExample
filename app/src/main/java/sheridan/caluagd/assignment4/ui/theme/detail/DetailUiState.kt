package sheridan.caluagd.assignment4.ui.theme.detail

import sheridan.caluagd.assignment4.model.Mars

sealed interface DetailsUiState {
    object Loading : DetailsUiState
    data class Success(val mars: Mars) : DetailsUiState
}