package mbitsystem.com.fileviewer.data

import androidx.work.Worker
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import mbitsystem.com.fileviewer.data.db.AppDatabase
import mbitsystem.com.fileviewer.data.model.File
import javax.inject.Inject

private const val DATA = "files.json"

class SeedDatabaseWorkerFiles @Inject constructor(val appDatabase: AppDatabase) : Worker() {

    override fun doWork(): Worker.Result {
        val nameType = object : TypeToken<List<File>>() {}.type
        var jsonReader: JsonReader? = null

        return try {
            val inputStream = applicationContext.assets.open(DATA)
            jsonReader = JsonReader(inputStream.reader())
            val nameList: List<File> = Gson().fromJson(jsonReader, nameType)
            appDatabase.fileDao().insertAll(nameList)
            Worker.Result.SUCCESS
        } catch (ex: Exception) {
            Worker.Result.FAILURE
        } finally {
            jsonReader?.close()
        }
    }
}