package kg.izapp.youtubeparser2.ui.playlists

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kg.izapp.youtubeparser2.R
import kg.izapp.youtubeparser2.base.BaseActivity
import kg.izapp.youtubeparser2.data.Info
import kg.izapp.youtubeparser2.ui.adapters.PlaylistsAdapter
import kg.izapp.youtubeparser2.ui.video_list.VideoListActivity
import kg.izapp.youtubeparser2.utills.showToast
import kotlinx.android.synthetic.main.activity_playlists.*

class PlaylistsActivity : BaseActivity<PlaylistsViewModel>(R.layout.activity_playlists,PlaylistsViewModel::class.java),PlaylistsAdapter.Listener {

    private lateinit var adapter: PlaylistsAdapter
    private val layoutManager = LinearLayoutManager(
        this,
        RecyclerView.VERTICAL, false
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initAdapter()
        viewModel.fetchPlaylists()
        populateAdapter()
    }

    private fun populateAdapter() {
        viewModel.info.observe(this, Observer {
            if (it!=null) {
                adapter.addItems(it.items)
            }
        })
    }

    private fun initAdapter(){
        adapter = PlaylistsAdapter(this)
        rv_playlists.itemAnimator = DefaultItemAnimator()
        rv_playlists.layoutManager = layoutManager
        rv_playlists.adapter = adapter
        rv_playlists.isNestedScrollingEnabled = true
    }

    override fun onItemClicked(item: Info) {
        var intent = Intent(this,VideoListActivity::class.java)
        intent.putExtra("ID",item.id)
        startActivity(intent)
    }
}