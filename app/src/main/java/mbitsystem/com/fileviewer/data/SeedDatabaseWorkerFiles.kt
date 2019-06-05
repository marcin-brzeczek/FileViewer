package mbitsystem.com.fileviewer.data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import mbitsystem.com.fileviewer.InitApp
import mbitsystem.com.fileviewer.R
import mbitsystem.com.fileviewer.data.db.AppDatabase
import mbitsystem.com.fileviewer.data.model.File
import org.jetbrains.anko.longToast
import javax.inject.Inject

private const val DATA = "files.json"

class SeedDatabaseWorkerFiles @Inject constructor(val appDatabase: AppDatabase, val application: InitApp) {

    fun populateDatabase() {
        val nameType = object : TypeToken<List<File>>() {}.type
        var jsonReader: JsonReader? = null
        return try {
            val inputStream = application.assets.open(DATA)
            jsonReader = JsonReader(inputStream.reader())
            val nameList: List<File> = Gson().fromJson(jsonReader, nameType)
            appDatabase.fileDao().insertAll(nameList)
        } catch (ex: Exception) {
            application.baseContext.let {
                it.longToast(it.getString(R.string.error_populate_database) + ex.message)
            }
        } finally {
            jsonReader?.close()
        }
    }
}