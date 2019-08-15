package mbitsystem.com.fileviewer

import io.reactivex.Observable
import mbitsystem.com.fileviewer.data.Interactor
import mbitsystem.com.fileviewer.data.model.File
import mbitsystem.com.fileviewer.data.repository.FileRepository
import mbitsystem.com.fileviewer.main.IMainPresenter
import mbitsystem.com.fileviewer.main.MainView
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FileInteractorTests : BaseTest() {

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    private lateinit var mainPresenter: IMainPresenter

    @Mock
    private lateinit var fileInteractor: Interactor

    @Mock
    private lateinit var mainView: MainView

    @Mock
    private lateinit var repository: FileRepository

    @Before
    fun setUp() {
        mainPresenter.bind(mainView)
    }

    private val dummyAllFiles: List<File>
        get() {
            val dummyFileList = listOf(
                File(
                    "11",
                    "Sample A",
                    "https://s3-us-west-2.amazonaws.com/android-task/sample+3.pdf",
                    "Description A is here",
                    "May 13, 2019"
                ),
                File(
                    "99",
                    "Sample B",
                    "https://s3-us-west-2.amazonaws.com/android-task/sample+3.pdf",
                    "Description B is here",
                    "May 14, 2019"
                ),
                File(
                    "989",
                    "Sample C",
                    "https://s3-us-west-2.amazonaws.com/android-task/sample+3.pdf",
                    "Description C is here",
                    "May 15, 2019"
                )
            )
            return dummyFileList
        }

        @Test
    fun `get files from database order by name ascending`() {
        val dummyFiles = dummyAllFiles
        Mockito.doReturn(Observable.just(dummyFiles)).`when`(repository).getFilesObservable()

        mainPresenter.getFilesObservable()

        Mockito.verify(repository).
//        Mockito.verify(mainView).render(FileState.DataState(emptyList()))
    }

//    @Test
//    fun testGetEmptyList() {
//
//        mainView.getFilesAscendingIntent()
//        //1
////        Mockito.doReturn(Observable.just(ArrayList<File>())).`when`(repository).getAllOrderByNameAscending()
//        //2
////        mainPresenter.deleteFile()
//        //3
//
//        Mockito.verify( mainView).getFilesAscendingIntent()
//    }
}