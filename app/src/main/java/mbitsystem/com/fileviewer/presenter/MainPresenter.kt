package mbitsystem.com.fileviewer.presenter

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import mbitsystem.com.fileviewer.data.FileInteractor
import mbitsystem.com.fileviewer.domain.FileState
import mbitsystem.com.fileviewer.view.MainView
import timber.log.Timber

class MainPresenter(private val fileInteractor: FileInteractor) {

    private lateinit var view: MainView
    private val compositeDisposable = CompositeDisposable()

    fun bind(view: MainView) {
        this.view = view
        compositeDisposable.add(displayAllFiles())
        compositeDisposable.add(displayFilesAsceding())
        compositeDisposable.add(displayFilesDesceding())
        compositeDisposable.add(deleteFile())
    }

    fun unbind() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }

    private fun displayAllFiles() = view.getFilesIntent()
        .doOnNext { Timber.d("Intent: Display Files") }
        .flatMap<FileState> { fileInteractor.getFiles() }
        .startWith(FileState.LoadingState)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe { view.render(it) }


    private fun displayFilesDesceding() = view.getFilesDescedingIntent()
        .doOnNext { Timber.d("Intent: Display Files Desceding") }
        .flatMap<FileState> { fileInteractor.getFilesDesceding() }
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe { view.render(it) }


    private fun displayFilesAsceding() = view.getFilesAscedingIntent()
        .doOnNext { Timber.d("Intent: Display Files Asceding") }
        .flatMap<FileState> { fileInteractor.getFilesAsceding() }
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe { view.render(it) }

    private fun deleteFile() = view.deleteMovieIntent()
        .doOnNext { Timber.d("Intent: Delete file") }
        .subscribeOn(AndroidSchedulers.mainThread())
        .observeOn(Schedulers.io())
        .flatMap<Unit> { fileInteractor.deleteFile(it) }
        .subscribe()
}