package mbitsystem.com.fileviewer.injection

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Module
import dagger.Provides
import mbitsystem.com.fileviewer.InitApp
import mbitsystem.com.fileviewer.data.SeedDatabaseWorkerFiles
import mbitsystem.com.fileviewer.data.dao.FileDao
import mbitsystem.com.fileviewer.data.db.AppDatabase
import mbitsystem.com.fileviewer.data.repository.FileRepository
import mbitsystem.com.fileviewer.data.repository.IRepository
import mbitsystem.com.fileviewer.utils.ioThread
import javax.inject.Singleton

private const val DB_NAME = "FileViewerDatabase"

@Module
class RoomModule(application: InitApp) {

    private var appDatabase: AppDatabase

    init {
        appDatabase = Room.databaseBuilder(application, AppDatabase::class.java, DB_NAME)
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    ioThread {
                        SeedDatabaseWorkerFiles(providesAppDatabase(), application).populateDatabase()
                    }
                }
            })
            .build()
    }

    @Singleton
    @Provides
    fun providesAppDatabase(): AppDatabase = appDatabase

    @Singleton
    @Provides
    fun providesFileDao(appDatabase: AppDatabase): FileDao = appDatabase.fileDao()


    @Singleton
    @Provides
    fun providesFileRepository(fileDao: FileDao): IRepository = FileRepository(fileDao)
}