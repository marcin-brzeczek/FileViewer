package mbitsystem.com.fileviewer.details

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import mbitsystem.com.fileviewer.data.FileInteractor
import mbitsystem.com.fileviewer.state.FileState
import timber.log.Timber

class DetailsPresenter(private val fileInteractor: FileInteractor) {

    private val compositeDisposable = CompositeDisposable()
    private lateinit var view: DetailsView

    fun bind(view: DetailsView) {
        this.view = view
        compositeDisposable.add(observeFileDisplayIntent())
    }

    fun unbind() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }

    private fun observeFileDisplayIntent() = view.displayFileIntent()
        .doOnNext { Timber.d("Intent: Display File pdf") }
        .flatMap<FileState> { fileInteractor.getFileInputStream(it.url)}
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe { view.render(FileState.LoadingState) }
        .subscribe { view.render(it) }
}