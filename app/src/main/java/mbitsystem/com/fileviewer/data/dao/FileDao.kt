package mbitsystem.com.fileviewer.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Observable
import mbitsystem.com.fileviewer.data.model.File

@Dao
interface FileDao {
    @Query("select * from files order by filename asc")
    fun getAllOrderByNameAscending(): Observable<List<File>>

    @Query("select * from files order by filename desc")
    fun getAllOrderByNameDescending(): Observable<List<File>>

    @Delete
    fun deleteFile(file: File): Completable

    @Insert
    fun insertAll(files: List<File>)
}