package mbitsystem.com.fileviewer.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Observable
import mbitsystem.com.fileviewer.data.model.File

@Dao
interface FileDao {
    @Query("select * from files order by filename asc")
    fun getAllOrderByNameAsceding(): Observable<List<File>>

    @Query("select * from files order by filename desc")
    fun getAllOrderByNameDesceding(): Observable<List<File>>

    @Insert
    fun insertAll(files: List<File>)
}