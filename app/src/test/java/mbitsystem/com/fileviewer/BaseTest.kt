package mbitsystem.com.fileviewer

import org.mockito.ArgumentCaptor

open class BaseTest {
    open fun <T> cuptureArg(argumentCaptor: ArgumentCaptor<T>): T = argumentCaptor.capture()
}