package kg.izapp.youtubeparser2.ui.video_list

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kg.izapp.youtubeparser2.R
import kg.izapp.youtubeparser2.base.BaseActivity
import kg.izapp.youtubeparser2.ui.adapters.VideoListAdapter
import kg.izapp.youtubeparser2.ui.video_details.VideoDetailsActivity
import kotlinx.android.synthetic.main.activity_video_list.*

class VideoListActivity : BaseActivity<VideoListViewModel>(R.layout.activity_video_list,VideoListViewModel::class.java),VideoListAdapter.Listener {

    lateinit var adapter: VideoListAdapter

    private val layoutManager = LinearLayoutManager(
        this,
        RecyclerView.VERTICAL, false
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = intent.getStringExtra("ID")
        viewModel.fetchVideoList(id?:"")
        initAdapter()
        subscribeToLiveData()
    }

    private fun subscribeToLiveData() {
        viewModel.info.observe(this, Observer {
            adapter.addItems(it.items)
        })
    }

    private fun initAdapter() {
        adapter = VideoListAdapter(this)
        rv_videos.layoutManager = layoutManager
        rv_videos.itemAnimator = DefaultItemAnimator()
        rv_videos.layoutManager = layoutManager
        rv_videos.adapter = adapter
        rv_videos.isNestedScrollingEnabled = true
    }

    override fun onItemClicked(id: String) {
        val intent = Intent(this,VideoDetailsActivity::class.java)
        intent.putExtra("ID",id)
        startActivity(intent)
    }


}