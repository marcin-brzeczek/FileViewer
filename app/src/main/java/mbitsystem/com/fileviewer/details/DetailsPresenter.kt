package mbitsystem.com.fileviewer.details

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import mbitsystem.com.fileviewer.data.FileInteractor
import mbitsystem.com.fileviewer.state.FileState
import timber.log.Timber
import javax.inject.Inject

class DetailsPresenter @Inject constructor(val fileInteractor: FileInteractor) : IDetailsPresenter {

    private val compositeDisposable = CompositeDisposable()
    private lateinit var view: DetailsView

    override fun bind(view: DetailsView) {
        this.view = view
        compositeDisposable.add(observeFileDisplayIntent())
    }

    override fun unbind() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }

    override fun observeFileDisplayIntent(): Disposable = view.displayFileIntent()
        .doOnNext { Timber.d("Intent: Display File pdf") }
        .flatMap<FileState> { fileInteractor.getFileInputStream(it.url) }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe { view.render(FileState.LoadingState) }
        .subscribe { view.render(it) }
}