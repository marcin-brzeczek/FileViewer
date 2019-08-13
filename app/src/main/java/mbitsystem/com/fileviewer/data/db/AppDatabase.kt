package mbitsystem.com.fileviewer.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import mbitsystem.com.fileviewer.data.dao.FileDao
import mbitsystem.com.fileviewer.data.model.File

@Database(entities = [ File::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun fileDao(): FileDao
}
