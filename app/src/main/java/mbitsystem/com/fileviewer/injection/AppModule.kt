package mbitsystem.com.fileviewer.injection

import dagger.Module
import dagger.Provides
import mbitsystem.com.fileviewer.InitApp
import mbitsystem.com.fileviewer.data.FileInteractor
import mbitsystem.com.fileviewer.data.Interactor
import mbitsystem.com.fileviewer.data.repository.FileRepository
import mbitsystem.com.fileviewer.details.DetailsPresenter
import mbitsystem.com.fileviewer.details.IDetailsPresenter
import mbitsystem.com.fileviewer.main.IMainPresenter
import mbitsystem.com.fileviewer.main.MainPresenter
import javax.inject.Singleton

@Module
class AppModule(val application: InitApp) {

    @Provides
    @Singleton
    fun providesApplication(): InitApp {
        return application
    }

    @Singleton
    @Provides
    fun providesFileInteractor(fileRepository: FileRepository): Interactor = FileInteractor(fileRepository)

    @Singleton
    @Provides
    fun providesMainPresenter(fileInteractor: FileInteractor): IMainPresenter = MainPresenter(fileInteractor)

    @Singleton
    @Provides
    fun providesDetailsPresenter(fileInteractor: FileInteractor): IDetailsPresenter = DetailsPresenter(fileInteractor)
}