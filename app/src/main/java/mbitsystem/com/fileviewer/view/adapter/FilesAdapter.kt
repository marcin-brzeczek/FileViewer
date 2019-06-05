package mbitsystem.com.fileviewer.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_file.view.*
import mbitsystem.com.fileviewer.KEY_INTENT_FILE
import mbitsystem.com.fileviewer.R
import mbitsystem.com.fileviewer.data.model.File
import mbitsystem.com.fileviewer.view.activity.DetailsActivity
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.sdk27.coroutines.onClick

class FilesAdapter : ListAdapter<File, FilesAdapter.FileHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_file, parent, false)
        return FileHolder(view)
    }

    val items: MutableList<File> = mutableListOf()

    fun sumbitList(newItems: Collection<File>) {
        items.clear()
        items.addAll(newItems)
        super.submitList(items)
    }

    override fun onBindViewHolder(holder: FileHolder, position: Int) = holder.bind(getItem(position))

    inner class FileHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(file: File) = with(view) {
            filename.text = file.filename
            date.text = file.date
            if (file.url.endsWith(context.getString(R.string.pdf_extension_file))) {
                file_image.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_pdf, null))
            }
            view.onClick {
                with(context) {
                    startActivity(intentFor<DetailsActivity>(KEY_INTENT_FILE to file))
                }
            }
        }
    }

    internal fun getFileAtPosition(position: Int) = items[position]
}


class DiffCallback : DiffUtil.ItemCallback<File>() {
    override fun areItemsTheSame(oldItem: File, newItem: File): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: File, newItem: File): Boolean =
        oldItem.date == newItem.date &&
                oldItem.filename == newItem.filename
}