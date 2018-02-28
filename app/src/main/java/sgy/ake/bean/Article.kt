package sgy.ake.bean

/**
 * Created by shiguiyou on 2018/2/28.
 */
data class Article(val curPage: Int,
                   val datas: List<Item>,
                   val offset: Int,
                   val over: Boolean,
                   val pageCount: Int,
                   val size: Int,
                   val total: Int)