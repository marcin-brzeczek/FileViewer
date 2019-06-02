package mbitsystem.com.fileviewer.view

import io.reactivex.Observable
import mbitsystem.com.fileviewer.domain.FileState

interface MainView {
    fun render(state: FileState)
    fun getFilesIntent(): Observable<Unit>
    fun getFilesDescedingIntent(): Observable<Unit>
    fun getFilesAscedingIntent(): Observable<Unit>
}
