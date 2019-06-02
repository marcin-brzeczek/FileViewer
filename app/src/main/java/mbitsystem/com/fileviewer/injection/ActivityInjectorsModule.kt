package mbitsystem.com.fileviewer.injection

import dagger.Module
import dagger.android.ContributesAndroidInjector
import mbitsystem.com.fileviewer.view.activity.DetailsActivity
import mbitsystem.com.fileviewer.view.activity.MainActivity

@Module
abstract class ActivityInjectorsModule {
    @ContributesAndroidInjector abstract fun provideMainActivityInjector(): MainActivity
    @ContributesAndroidInjector abstract fun provideDetailsActivityInjector(): DetailsActivity
}