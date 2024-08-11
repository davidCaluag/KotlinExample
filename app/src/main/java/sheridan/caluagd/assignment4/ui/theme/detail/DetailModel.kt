@file:JvmName("DetailUiStateKt")

package sheridan.caluagd.assignment4.ui.theme.detail

import sheridan.caluagd.assignment4.database.local.LocalMars
import sheridan.caluagd.assignment4.model.Mars

data class DetailModel(
    val id: String,
    val uriSrc: String
){
    constructor(marsPhoto: Mars): this(
        id = marsPhoto.id.toString(),
        uriSrc = marsPhoto.imgSrc
    )

}

fun Mars.toLocal(): LocalMars = LocalMars(id = id, imgSrc = imgSrc)

fun Mars.toDetailModel():DetailModel =
    DetailModel(this)

