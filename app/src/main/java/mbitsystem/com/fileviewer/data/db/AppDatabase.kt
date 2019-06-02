package mbitsystem.com.fileviewer.data.db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import mbitsystem.com.fileviewer.data.SeedDatabaseWorkerFiles
import mbitsystem.com.fileviewer.data.dao.FileDao
import mbitsystem.com.fileviewer.data.model.File

@Database(entities = [ File::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun fileDao(): FileDao

}
//    companion object {
//        private val lock = Any()
//        private const val DB_NAME = "FileViewerDatabase"
//        private var INSTANCE: AppDatabase? = null
//
//        fun getInstance(application: Application): AppDatabase {
//            synchronized(AppDatabase.lock) {
//                if (AppDatabase.INSTANCE == null) {
//                    AppDatabase.INSTANCE =
//                        Room.databaseBuilder(application, AppDatabase::class.java, AppDatabase.DB_NAME)
//                            .addCallback(object : RoomDatabase.Callback() {
//                                override fun onCreate(db: SupportSQLiteDatabase) {
//                                    super.onCreate(db)
//                                    val requestInsertFiles = OneTimeWorkRequestBuilder<SeedDatabaseWorkerFiles>().build()
//                                    WorkManager.getInstance().enqueue(requestInsertFiles)
//                                }
//                            })
//                            .build()
//                }
//            }
//            return INSTANCE!!
//        }
//    }
//}