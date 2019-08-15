package mbitsystem.com.fileviewer.main

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import mbitsystem.com.fileviewer.data.FileInteractor
import mbitsystem.com.fileviewer.data.model.File
import mbitsystem.com.fileviewer.state.FileState
import timber.log.Timber
import javax.inject.Inject

class MainPresenter @Inject constructor(val fileInteractor: FileInteractor) : IMainPresenter {

    lateinit var view: MainView
    private val compositeDisposable = CompositeDisposable()

    override fun bind(view: MainView) {
        this.view = view
        compositeDisposable.add(displayAllFiles())
        compositeDisposable.add(displayFilesAscending())
        compositeDisposable.add(displayFilesDescending())
        compositeDisposable.add(deleteFile())
    }

    override fun unbind() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }

    override fun getFilesObservable(): Observable<List<File>> = fileInteractor.getFilesObservable()

    override fun displayAllFiles() = view.getFilesIntent()
        .doOnNext { Timber.d("Intent: Display Files") }
        .flatMap<FileState> { fileInteractor.getFiles() }
        .startWith(FileState.LoadingState)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe { view.render(it) }


    override fun displayFilesDescending() = view.getFilesDescendingIntent()
        .doOnNext { Timber.d("Intent: Display Files Desceding") }
        .flatMap<FileState> { fileInteractor.getFilesDesceding() }
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe { view.render(it) }


    override fun displayFilesAscending() = view.getFilesAscendingIntent()
        .doOnNext { Timber.d("Intent: Display Files Asceding") }
        .flatMap<FileState> { fileInteractor.getFilesAsceding() }
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe { view.render(it) }

    override fun deleteFile() = view.deleteMovieIntent()
        .doOnNext { Timber.d("Intent: Delete file") }
        .subscribeOn(AndroidSchedulers.mainThread())
        .observeOn(Schedulers.io())
        .flatMap<Unit> { fileInteractor.deleteFile(it) }
        .subscribe()
}