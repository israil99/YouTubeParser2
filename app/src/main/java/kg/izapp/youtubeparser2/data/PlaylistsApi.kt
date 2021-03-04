package kg.izapp.youtubeparser2.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaylistsApi{

    @GET("youtube/v3/playlists")
    fun fetchPlaylists(
        @Query("part") part: String,
        @Query("channelId") channelId: String,
        @Query("key") key: String,
        @Query("pageToken") pageToken: String?
    ): Call<VideoInfo>

    @GET("youtube/v3/playlistItems")
    fun fetchVideoList(
        @Query("part") part: String,
        @Query("pageToken") pageToken: String?,
        @Query("playlistId") playlistId: String,
        @Query("key") key: String
    ): Call<VideoInfo>

}