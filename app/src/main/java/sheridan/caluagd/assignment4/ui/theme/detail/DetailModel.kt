@file:JvmName("DetailUiStateKt")

package sheridan.caluagd.assignment4.ui.theme.detail

import sheridan.caluagd.assignment4.model.MarsPhoto

data class DetailModel(
    val id: String,
    val uriSrc: String
){
    constructor(marsPhoto: MarsPhoto): this(
        id = marsPhoto.id,
        uriSrc = marsPhoto.imgSrc
    )

}

fun MarsPhoto.toDetailModel():DetailModel =
    DetailModel(this)

