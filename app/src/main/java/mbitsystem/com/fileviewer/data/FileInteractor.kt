package mbitsystem.com.fileviewer.data

import io.reactivex.Observable
import mbitsystem.com.fileviewer.data.model.File
import mbitsystem.com.fileviewer.data.repository.FileRepository
import mbitsystem.com.fileviewer.state.FileState
import java.io.BufferedInputStream
import java.io.IOException
import java.io.InputStream
import java.net.URL
import javax.inject.Inject

class FileInteractor @Inject constructor(val fileRepository: FileRepository) : Interactor {

    override fun getFilesAsceding(): Observable<FileState> = fileRepository.getAllOrderByNameAscending()
        .map<FileState> { FileState.DataState(it) }
        .onErrorReturn { FileState.ErrorState(it.message) }

    override fun getFilesDesceding(): Observable<FileState> = fileRepository.getAllOrderByNameDescending()
        .map<FileState> { FileState.DataState(it) }
        .onErrorReturn { FileState.ErrorState(it.message) }

    override fun getFilesObservable(): Observable<List<File>> = fileRepository.getFilesObservable()

    override fun getFiles(): Observable<FileState> = fileRepository.getAllOrderByNameAscending()
        .map<FileState> { FileState.DataState(it) }
        .onErrorReturn { FileState.ErrorState(it.message) }

    override fun deleteFile(file: File): Observable<Unit> = fileRepository.deleteFile(file).toObservable()

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