package kg.izapp.youtubeparser2.ui.video_list

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kg.izapp.youtubeparser2.base.BaseViewModel
import kg.izapp.youtubeparser2.data.VideoInfo
import kg.izapp.youtubeparser2.repo.PlaylistsRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VideoListViewModel : BaseViewModel() {

    val repository = PlaylistsRepository()
    val info = MutableLiveData<VideoInfo>()

    fun fetchVideoList(id:String){
        repository.fetchVideoList(id).enqueue(object : Callback<VideoInfo> {
            override fun onResponse(call: Call<VideoInfo>, response: Response<VideoInfo>) {
                if (response.isSuccessful)
                    response.body()?.let {
                        info.value = response.body()
                    }
            }

            override fun onFailure(call: Call<VideoInfo>, t: Throwable) {
                t.printStackTrace()
                Log.e("E", ": " + t.message)
            }

        })
    }
}