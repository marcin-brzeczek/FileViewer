package mbitsystem.com.fileviewer.data.repository

import mbitsystem.com.fileviewer.data.dao.FileDao
import mbitsystem.com.fileviewer.data.model.File
import javax.inject.Inject

open class FileRepository @Inject constructor(val fileDao: FileDao) : IRepository {

    override fun insertAll(files: List<File>) = fileDao.insertAll(files)
}