package mbitsystem.com.fileviewer.main

import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import mbitsystem.com.fileviewer.data.model.File

interface IMainPresenter {
    fun bind(view: MainView)
    fun unbind()
    fun displayAllFiles(): Disposable
    fun displayFilesDescending(): Disposable
    fun displayFilesAscending(): Disposable
    fun getFilesObservable(): Observable<List<File>>
    fun deleteFile(): Disposable
}