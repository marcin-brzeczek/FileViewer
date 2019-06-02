package mbitsystem.com.fileviewer.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import paperparcel.PaperParcel
import paperparcel.PaperParcelable

@PaperParcel
@Entity(tableName = "files")
data class File(
    @PrimaryKey
    val id: String,
    val filename: String,
    val url: String,
    val description: String,
    val date: String
) : PaperParcelable {
    override fun toString() = filename

    companion object {
        @JvmField
        val CREATOR = PaperParcelFile.CREATOR
    }
}