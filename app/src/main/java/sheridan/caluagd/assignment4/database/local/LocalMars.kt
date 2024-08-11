package sheridan.caluagd.assignment4.database.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "mars")
data class LocalMars(
    @PrimaryKey(autoGenerate = true) @ColumnInfo("r_id")
    val id: Int,
    @ColumnInfo("imgSrc")
    val imgSrc: String
){

}
