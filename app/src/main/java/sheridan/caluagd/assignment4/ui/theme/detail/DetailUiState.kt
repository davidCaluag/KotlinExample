package sheridan.caluagd.assignment4.ui.theme.detail

import sheridan.caluagd.assignment4.model.MarsPhoto

data class DetailUiState(var detailModel: DetailModel){
    constructor(marsPhoto: MarsPhoto):this(
        detailModel = marsPhoto.toDetailModel()
    )
    constructor():this(MarsPhoto())
}
