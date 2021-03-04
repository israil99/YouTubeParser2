package kg.izapp.youtubeparser2.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kg.izapp.youtubeparser2.R
import kg.izapp.youtubeparser2.data.Info
import kotlinx.android.synthetic.main.item_playlist.view.*

class VideoListAdapter(var listener: Listener): RecyclerView.Adapter<BaseViewHolder>() {

    var list = mutableListOf<Info>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_playlist,parent,false)
        return VideoListViewHolder(view)
    }

    override fun getItemCount() = list.size

    fun addItems(list: MutableList<Info>){
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    inner class VideoListViewHolder(itemView: View):BaseViewHolder(itemView){
        override fun onBind(position: Int) {
            val picasso = Picasso.get()
            itemView.apply {
                title.text = list[position].snippet?.title
                subtitle.text = list[position].snippet?.description
                picasso.load(list[position].snippet?.thumbnails?.medium?.url)
                    .into(this.icon)
                setOnClickListener {
                    listener.onItemClicked(list[position].contentDetails?.videoId?:"")
                }
            }
        }
    }

    interface Listener{
        fun onItemClicked(id: String)
    }

}