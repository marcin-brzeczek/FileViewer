package mbitsystem.com.fileviewer.data

import io.reactivex.Observable
import mbitsystem.com.fileviewer.data.repository.FileRepository
import mbitsystem.com.fileviewer.domain.FileState
import java.io.BufferedInputStream
import java.io.IOException
import java.io.InputStream
import java.net.URL

class FileInteractor(private val fileRepository: FileRepository) : Interactor {

    override fun getFilesAsceding(): Observable<FileState> = fileRepository.fileDao.getAllOrderByNameAsceding()
        .map<FileState> { FileState.DataState(it) }
        .onErrorReturn { FileState.ErrorState(it.message) }

    override fun getFilesDesceding(): Observable<FileState> = fileRepository.fileDao.getAllOrderByNameDesceding()
        .map<FileState> { FileState.DataState(it) }
        .onErrorReturn { FileState.ErrorState(it.message) }

    override fun getFiles(): Observable<FileState> = fileRepository.fileDao.getAllOrderByNameAsceding()
        .map<FileState> { FileState.DataState(it) }
        .onErrorReturn { FileState.ErrorState(it.message) }

    fun getFileInputStream(urlString: String): Observable<FileState> {
        return Observable.just(urlString)
            .map<FileState> { FileState.LoadFileFromStream((getInputStreamFromUrlString(it))) }
            .onErrorReturn { FileState.ErrorState(it.message) }
    }

    private fun getInputStreamFromUrlString(urlString: String): InputStream {
        return try {
            val inputStream = URL(urlString).openConnection().getInputStream()
            BufferedInputStream(inputStream)
        } catch (e: IOException) {
            throw IOException(e.message)
        }
    }
}