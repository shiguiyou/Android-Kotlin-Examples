package sgy.ake.bean

/**
 * Created by shiguiyou on 2018/2/28.
 */
data class HttpResult<T>(val data: T,
                         val errorCode: Int,
                         val errorMsg: String)