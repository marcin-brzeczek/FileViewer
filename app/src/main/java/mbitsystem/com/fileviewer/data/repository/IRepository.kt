package mbitsystem.com.fileviewer.data.repository

import mbitsystem.com.fileviewer.data.model.File

interface IRepository{

    fun insertAll(files: List<File>)
}