package sgy.ake.model

/**
 * Created by shiguiyou on 2017/8/24.
 */
data class DetailModel(
        val questions: List<Question>,
        val answers: List<Question>,
        val favorites: List<Question>
)