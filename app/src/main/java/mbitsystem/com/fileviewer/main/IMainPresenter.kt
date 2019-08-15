package mbitsystem.com.fileviewer.main

import io.reactivex.disposables.Disposable

interface IMainPresenter {
    fun bind(view: MainView)
    fun unbind()
    fun displayAllFiles(): Disposable
    fun displayFilesDescending(): Disposable
    fun displayFilesAscending(): Disposable
    fun deleteFile(): Disposable
}