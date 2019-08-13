package mbitsystem.com.fileviewer.state

import mbitsystem.com.fileviewer.data.model.File
import java.io.InputStream

sealed class FileState {
    object LoadingState : FileState()
    data class DataState(val data: List<File>) : FileState()
    data class LoadFileFromStream(val stream: InputStream) : FileState()
    data class ErrorState(val error: String?) : FileState()
}