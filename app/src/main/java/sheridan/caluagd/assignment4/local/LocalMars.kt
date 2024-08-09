package sheridan.caluagd.assignment4.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "mars")
data class LocalMars(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val imgSrc: String
){

}
