package mbitsystem.com.fileviewer.main

import io.reactivex.Observable
import mbitsystem.com.fileviewer.data.model.File
import mbitsystem.com.fileviewer.state.FileState

interface MainView {
    fun render(state: FileState)
    fun getFilesIntent(): Observable<Unit>
    fun getFilesDescendingIntent(): Observable<Unit>
    fun getFilesAscendingIntent(): Observable<Unit>
    fun deleteMovieIntent(): Observable<File>
}
