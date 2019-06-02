package mbitsystem.com.fileviewer.injection

import dagger.Module
import javax.inject.Singleton
import dagger.Provides
import mbitsystem.com.fileviewer.InitApp

@Module
class AppModule(val application: InitApp) {

    @Provides
    @Singleton
    fun providesApplication(): InitApp {
        return application
    }
}