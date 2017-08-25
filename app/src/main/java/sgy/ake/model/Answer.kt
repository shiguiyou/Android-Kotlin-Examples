package sgy.ake.model

import com.google.gson.annotations.SerializedName

/**
 * Created by shiguiyou on 2017/8/24.
 */
data class Answer(
        val score: Int,
        @SerializedName("question_id") val questionId: Int,
        @SerializedName("is_accepted") val accept:Boolean
)