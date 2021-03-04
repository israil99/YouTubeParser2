package kg.izapp.youtubeparser2.repo

import kg.izapp.youtubeparser2.data.VideoInfo
import kg.izapp.youtubeparser2.utills.RetrofitClient
import retrofit2.Call

class PlaylistsRepository {

    private val channelId = "UCLJl8-mbCfolWMkh1F1qfjA"
    private val part = "snippet,contentDetails"
     val API_KEY  = "AIzaSyBsjfh82smbB8GWuSesk8hJOgV7VGS75rs"

    val retrofitClient = RetrofitClient()

    fun fetchPlaylists(pageToken: String?): Call<VideoInfo> {
        return retrofitClient.instanceRetrofit().fetchPlaylists(part,channelId,API_KEY,pageToken)
    }

    fun fetchVideoList(id: String): Call<VideoInfo>{
        return retrofitClient.instanceRetrofit().fetchVideoList(part,"",id,API_KEY)
    }


}