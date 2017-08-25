package sgy.ake.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import sgy.ake.model.Answer
import sgy.ake.model.CommonResult
import sgy.ake.model.Question
import sgy.ake.model.User

/**
 * Created by shiguiyou on 2017/8/24.
 */
interface ApiService {

    @GET("/users?order=desc&sort=reputation&site=stackoverflow")
    fun getUsers(@Query("page") page: Int = 1): Single<CommonResult<User>>

    @GET("/users/{userId}/questions?order=desc&sort=votes&site=stackoverflow")
    fun getQuestionsByUser(@Path("userId") userId: Long): Single<CommonResult<Question>>

    @GET("/users/{userId}/favorites?order=desc&sort=votes&site=stackoverflow")
    fun getFavoritesByUser(@Path("userId") userId: Long): Single<CommonResult<Question>>

    @GET("/users/{userId}/answers?order=desc&sort=votes&site=stackoverflow")
    fun getAnswersByUser(@Path("userId") userId: Long): Single<CommonResult<Answer>>

    @GET("/questions/{questionIds}?order=desc&sort=activity&site=stackoverflow")
    fun getQuestionById(@Path("questionIds") questionId: String): Single<CommonResult<Question>>

}