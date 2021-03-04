package kg.izapp.youtubeparser2.utills

import kg.izapp.youtubeparser2.data.PlaylistsApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val BASE_URL = "https://www.googleapis.com/"

class RetrofitClient {

    private val httpInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okhttpClient : OkHttpClient = OkHttpClient().newBuilder() //клиент обертка ретрофита для того чтобы выставить огрничения по вреиени запроса и т.д
        .connectTimeout(40, TimeUnit.SECONDS)
        .readTimeout(40, TimeUnit.SECONDS)
        .writeTimeout(40, TimeUnit.SECONDS)
        .addInterceptor(httpInterceptor)
        .build()


    private val retrofit : Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okhttpClient)
        .build()



    fun instanceRetrofit(): PlaylistsApi {
        return retrofit.create(PlaylistsApi::class.java)
    }
}