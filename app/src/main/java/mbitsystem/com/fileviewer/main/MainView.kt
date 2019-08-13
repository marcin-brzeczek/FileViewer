package mbitsystem.com.fileviewer.main

import io.reactivex.Observable
import mbitsystem.com.fileviewer.data.model.File
import mbitsystem.com.fileviewer.state.FileState

interface MainView {
    fun render(state: FileState)
    fun getFilesIntent(): Observable<Unit>
    fun getFilesDescedingIntent(): Observable<Unit>
    fun getFilesAscedingIntent(): Observable<Unit>
    fun deleteMovieIntent(): Observable<File>
}
