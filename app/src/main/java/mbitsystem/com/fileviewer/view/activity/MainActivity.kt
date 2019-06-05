package mbitsystem.com.fileviewer.view.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_main.*
import mbitsystem.com.fileviewer.R
import mbitsystem.com.fileviewer.data.FileInteractor
import mbitsystem.com.fileviewer.data.model.File
import mbitsystem.com.fileviewer.data.repository.FileRepository
import mbitsystem.com.fileviewer.domain.FileState
import mbitsystem.com.fileviewer.presenter.MainPresenter
import mbitsystem.com.fileviewer.view.MainView
import mbitsystem.com.fileviewer.view.adapter.FilesAdapter
import org.jetbrains.anko.longToast
import timber.log.Timber
import javax.inject.Inject

class MainActivity : BaseActivity(), MainView {

    @Inject
    lateinit var fileRepository: FileRepository

    private lateinit var presenter: MainPresenter

    private val intentFilterAscedingPublisher = PublishSubject.create<Unit>()

    private val intentFilterDescedingPublisher = PublishSubject.create<Unit>()

    override fun render(state: FileState) {
        Timber.d("State: ${state.javaClass.simpleName}")
        when (state) {
            is FileState.LoadingState -> renderLoadingState()
            is FileState.DataState -> renderDataState(state)
            is FileState.ErrorState -> renderErrorState(state.error)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_view.adapter = FilesAdapter()
        recycler_view.layoutManager = LinearLayoutManager(this)
        presenter = MainPresenter(FileInteractor(fileRepository))
        presenter.bind(this)
    }

    override fun getFilesIntent(): Observable<Unit> = Observable.just(Unit)

    override fun getFilesDescedingIntent(): Observable<Unit> = intentFilterDescedingPublisher

    override fun getFilesAscedingIntent(): Observable<Unit> = intentFilterAscedingPublisher


    override fun deleteMovieIntent(): Observable<File> {
        val observable = Observable.create<File> { emitter ->
            // Add the functionality to swipe items in the
            // recycler view to delete that item
            val helper = ItemTouchHelper(
                object : ItemTouchHelper.SimpleCallback(
                    0,
                    ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
                ) {
                    // We are not implementing onMove() in this app
                    override fun onMove(
                        recyclerView: RecyclerView,
                        viewHolder: RecyclerView.ViewHolder,
                        target: RecyclerView.ViewHolder
                    ): Boolean {
                        return false
                    }

                    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                        val position = viewHolder.getAdapterPosition()
                        val file = (recycler_view.adapter as FilesAdapter).getFileAtPosition(position)
                        emitter.onNext(file)
                    }
                })

            helper.attachToRecyclerView(recycler_view)
        }
        return observable
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.filter_files, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.asceding -> {
                intentFilterAscedingPublisher.onNext(Unit)
                longToast("Asceding")
            }
            R.id.desceding -> {
                intentFilterDescedingPublisher.onNext(Unit)
                longToast("Desceding")
            }
        }
        return true
    }

    private fun renderLoadingState() {
        recycler_view.isEnabled = false
        progress_bar.visibility = View.VISIBLE
    }

    private fun renderDataState(dataState: FileState.DataState) {
        progress_bar.visibility = View.GONE
        recycler_view.apply {
            isEnabled = true
            (adapter as FilesAdapter).submitList(dataState.data)
        }
    }

    private fun renderErrorState(error: String?) = error?.let {
        longToast(getString(R.string.error_load_data) + it)
    }

    override fun onStop() {
        super.onStop()
        presenter.unbind()
    }
}