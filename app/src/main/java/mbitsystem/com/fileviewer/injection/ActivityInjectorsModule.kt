package mbitsystem.com.fileviewer.injection

import dagger.Module
import dagger.android.ContributesAndroidInjector
import mbitsystem.com.fileviewer.details.DetailsActivity
import mbitsystem.com.fileviewer.main.MainActivity

@Module
abstract class ActivityInjectorsModule {
    @ContributesAndroidInjector abstract fun provideMainActivityInjector(): MainActivity
    @ContributesAndroidInjector abstract fun provideDetailsActivityInjector(): DetailsActivity
}