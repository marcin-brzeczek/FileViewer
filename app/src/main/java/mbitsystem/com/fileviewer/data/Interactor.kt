package mbitsystem.com.fileviewer.data

import io.reactivex.Observable
import mbitsystem.com.fileviewer.domain.FileState

interface Interactor {
    fun getFiles(): Observable<FileState>
    fun getFilesAsceding(): Observable<FileState>
    fun getFilesDesceding(): Observable<FileState>
}