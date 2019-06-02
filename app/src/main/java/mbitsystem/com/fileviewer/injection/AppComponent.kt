package mbitsystem.com.fileviewer.injection

import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import mbitsystem.com.fileviewer.InitApp
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
        ActivityInjectorsModule::class,
        ActivityInjectorsModule::class,
        AppModule::class,
        RoomModule::class]
)
interface AppComponent : AndroidInjector<InitApp> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<InitApp>() {
        abstract fun setAppModule(module: AppModule)
        abstract fun setRoomModule(module: RoomModule)

        abstract override fun build(): AppComponent
    }
}