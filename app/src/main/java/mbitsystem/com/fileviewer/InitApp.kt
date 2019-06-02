package mbitsystem.com.fileviewer

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.HasActivityInjector
import mbitsystem.com.fileviewer.injection.AppModule
import mbitsystem.com.fileviewer.injection.DaggerAppComponent
import mbitsystem.com.fileviewer.injection.RoomModule

//lateinit var database: AppDatabase
//

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
}
/**

//        DaggerAppComponent.builder()
//            .appModule(AppModule(application))
//            .roomModule(RoomModule(application))
//            .build()
//            .inject(this)
}


//
//    companion object {
//        lateinit var INSTANCE: InitApp
//    }
//
//    init {
//        INSTANCE = this
//    }
//
//    override fun onCreate() {
//        super.onCreate()
//        database = AppDatabase.getInstance(this)
//        INSTANCE = this
//
//        if (BuildConfig.DEBUG) {
//            Timber.plant(Timber.DebugTree())
//        }
//    }
}**/