package mbitsystem.com.fileviewer.view

import io.reactivex.Observable
import mbitsystem.com.fileviewer.data.model.File
import mbitsystem.com.fileviewer.domain.FileState

interface DetailsView {
  fun render(state: FileState)
  fun displayFileIntent(): Observable<File>
}
