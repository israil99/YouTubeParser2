package kg.izapp.youtubeparser2.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.OrientationEventListener
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kg.izapp.youtubeparser2.R
import kg.izapp.youtubeparser2.data.Info
import kg.izapp.youtubeparser2.data.VideoInfo
import kotlinx.android.synthetic.main.item_playlist.view.*

class PlaylistsAdapter(var listener: Listener) : RecyclerView.Adapter<BaseViewHolder>() {

    var list = mutableListOf<Info>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_playlist, parent, false)
        return PlaylistsViewHolder(view)
    }

    fun addItems(list: MutableList<Info>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if (list.size > 0) {
            BaseViewHolder.VIEW_TYPE_NORMAL
        } else {
            BaseViewHolder.VIEW_TYPE_EMPTY
        }
    }

    override fun getItemCount() = list.size


    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    inner class PlaylistsViewHolder(itemView: View) : BaseViewHolder(itemView) {
        override fun onBind(position: Int) {
            val playlistsCount = list[position].contentDetails?.itemCount
            val picasso = Picasso.get()
            itemView.apply {
                picasso.load(list[position].snippet?.thumbnails?.medium?.url)
                    .into(this.icon)
                title.text = list[position].snippet?.title
                subtitle.text = "$playlistsCount video series"
                this.setOnClickListener {
                    listener.onItemClicked(list[position])
                }
            }

        }
    }

    interface Listener{
        fun onItemClicked(item: Info)
    }

}