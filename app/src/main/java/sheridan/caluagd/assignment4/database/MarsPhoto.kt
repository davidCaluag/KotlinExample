package sheridan.caluagd.assignment4.database

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MarsPhoto(
    @SerialName(value = "id")
    val id : Int,
    @SerialName(value = "img_src")
    val imgSrc: String = ""
)
