package mbitsystem.com.fileviewer.data.repository

import io.reactivex.Completable
import io.reactivex.Observable
import mbitsystem.com.fileviewer.data.dao.FileDao
import mbitsystem.com.fileviewer.data.model.File
import javax.inject.Inject

open class FileRepository @Inject constructor(val fileDao: FileDao) : IRepository {
    override fun getAllOrderByNameDescending(): Observable<List<File>> = fileDao.getAllOrderByNameDescending()

    override fun getAllOrderByNameAscending(): Observable<List<File>> = fileDao.getAllOrderByNameAscending()

    override fun deleteFile(file: File): Completable = fileDao.deleteFile(file)

    override fun insertAll(files: List<File>) = fileDao.insertAll(files)

    override fun getFilesObservable(): Observable<List<File>> = fileDao.getAllOrderByNameAscending()
}