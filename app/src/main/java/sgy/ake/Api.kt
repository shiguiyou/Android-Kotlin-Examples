package sgy.ake

import io.reactivex.Observable
import retrofit2.http.GET
import sgy.ake.bean.Article
import sgy.ake.bean.HttpResult

/**
 * Created by shiguiyou on 2018/2/28.
 */
interface Api {
    @GET("article/list/0/json")
    fun getArticles(): Observable<HttpResult<Article>>
}