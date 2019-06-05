package mbitsystem.com.fileviewer

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.HasActivityInjector
import mbitsystem.com.fileviewer.injection.AppModule
import mbitsystem.com.fileviewer.injection.DaggerAppComponent
import mbitsystem.com.fileviewer.injection.RoomModule
import timber.log.Timber

class InitApp : DaggerApplication(), HasActivityInjector {
    private val _applicationInjector by lazy {
        DaggerAppComponent.builder().let {
            it.seedInstance(this)
            it.setAppModule(AppModule(this))
            it.setRoomModule(RoomModule(this))
            it.build()
        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = _applicationInjector

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
