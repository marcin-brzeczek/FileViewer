package mbitsystem.com.fileviewer.details

import android.os.Bundle
import android.view.View
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_details.*
import mbitsystem.com.fileviewer.R
import mbitsystem.com.fileviewer.base.BaseActivity
import mbitsystem.com.fileviewer.data.model.File
import mbitsystem.com.fileviewer.main.MainActivity
import mbitsystem.com.fileviewer.state.FileState
import mbitsystem.com.fileviewer.utils.KEY_INTENT_FILE
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.longToast
import org.jetbrains.anko.newTask
import java.io.InputStream
import javax.inject.Inject

class DetailsActivity : BaseActivity(), DetailsView {

    @Inject
    lateinit var presenter: DetailsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        presenter.bind(this)
    }

    override fun render(state: FileState) {
        when (state) {
            is FileState.LoadingState -> renderLoadingState()
            is FileState.LoadFileFromStream -> renderLoadFile(state.stream)
            is FileState.ErrorState -> renderErrorState(state.error)
        }
    }

    override fun displayFileIntent(): Observable<File> =
        Observable.just(intent.extras.getParcelable(KEY_INTENT_FILE) as File)

    private fun renderLoadingState() {
        progress_bar.visibility = View.VISIBLE
    }

    private fun renderLoadFile(stream: InputStream) {
        progress_bar.visibility = View.GONE
        try {
            pdf_view.fromStream(stream).load()
        } catch (e: Exception) {
            longToast(getString(R.string.error_loading_pdf_file) + e.message)
        }
    }

    private fun renderErrorState(error: String?) {
        progress_bar.visibility = View.GONE
        error?.let { longToast(getString(R.string.error_loading_file) + it) }
    }

    override fun onStop() {
        presenter.unbind()
        super.onStop()
    }

    override fun onBackPressed() {
        startActivity(intentFor<MainActivity>().newTask().clearTask())
    }
}