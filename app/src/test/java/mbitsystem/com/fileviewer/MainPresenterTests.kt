package mbitsystem.com.fileviewer

import io.reactivex.Observable
import mbitsystem.com.fileviewer.data.FileInteractor
import mbitsystem.com.fileviewer.data.model.File
import mbitsystem.com.fileviewer.data.repository.FileRepository
import mbitsystem.com.fileviewer.main.MainPresenter
import mbitsystem.com.fileviewer.main.MainView
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainPresenterTests : BaseTest() {

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    private lateinit var mockActivity: MainView

    @Mock
    private lateinit var mockFileRepository: FileRepository

    lateinit var fileInteractor: FileInteractor

    lateinit var mainPresenter: MainPresenter

    @Before
    fun setUp() {
        fileInteractor = FileInteractor(mockFileRepository)
        mainPresenter = MainPresenter(fileInteractor)
        mainPresenter.bind(mockActivity)
    }

    private val dummyAllFiles: ArrayList<File>
        get() {
            val dummyFileList = ArrayList<File>()
            dummyFileList.add(File("1", "FileTest1", "urlPath", "", ""))
            dummyFileList.add(File("Title2", "ReleaseDate2", "PosterPath2", "", ""))
            dummyFileList.add(File("Title3", "ReleaseDate3", "PosterPath3", "", ""))
            dummyFileList.add(File("Title4", "ReleaseDate4th", "PosterPath4", "", ""))
            return dummyFileList
        }

    @Test
    fun `test_Get My Mo vieList`() {
        val dummyFiles = dummyAllFiles
        Mockito.doReturn(Observable.just(dummyFiles)).`when`(mockFileRepository).fileDao.getAllOrderByNameAsceding()

       mainPresenter.displayFilesAsceding()

        Mockito.verify(mockFileRepository).fileDao.getAllOrderByNameAsceding()
        Mockito.verify(mockActivity).getFilesAscedingIntent().
    }
//
//    @Test
//    fun testGetMyMoviesListWithNoMovies() {
//        //1
//        Mockito.doReturn(Observable.just(ArrayList<Movie>())).`when`(mockDataSource).allMovies
//        //2
//        mainPresenter.getMyMoviesList()
//        //3
//        Mockito.verify(mockDataSource).allMovies
//        Mockito.verify(mockActivity).displayNoMovies()
//    }
}