package sgy.ake.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by shiguiyou on 2017/8/24.
 */
object ApiHelper {

    private val BASE_URL = "https://api.stackexchange.com/2.2/"
    private val apiService: ApiService? = null

    fun getService(): ApiService {

        if (apiService == null) {
            val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            return retrofit.create(ApiService::class.java)
        }
        return apiService
    }
}