package mbitsystem.com.fileviewer.details

import io.reactivex.Observable
import mbitsystem.com.fileviewer.data.model.File
import mbitsystem.com.fileviewer.state.FileState

interface DetailsView {
  fun render(state: FileState)
  fun displayFileIntent(): Observable<File>
}
