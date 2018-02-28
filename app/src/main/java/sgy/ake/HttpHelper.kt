package sgy.ake

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by shiguiyou on 2018/2/28.
 */
object HttpHelper {
    val apiService: Api? = null
    val builder = OkHttpClient.Builder()

    fun getApi(): Api {
        builder.connectTimeout(10, TimeUnit.SECONDS)

        if (apiService == null) {
            val retrofit = Retrofit.Builder()
                    .client(builder.build())
                    .baseUrl("http://www.wanandroid.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
            return retrofit.create(Api::class.java)
        }
        return apiService
    }
}