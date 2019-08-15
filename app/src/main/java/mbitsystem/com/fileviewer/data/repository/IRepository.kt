package mbitsystem.com.fileviewer.data.repository

import io.reactivex.Completable
import io.reactivex.Observable
import mbitsystem.com.fileviewer.data.model.File

interface IRepository {
    fun insertAll(files: List<File>)
    fun getAllOrderByNameDescending(): Observable<List<File>>
    fun getAllOrderByNameAscending(): Observable<List<File>>
    fun deleteFile(file: File): Completable

    fun getFilesObservable(): Observable<List<File>>
}