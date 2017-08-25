package sgy.ake.model

import com.google.gson.annotations.SerializedName

/**
 * Created by shiguiyou on 2017/8/24.
 */
data class User(
        @SerializedName("display_name") val name: String,
        @SerializedName("profile_image") val avatar: String,
        @SerializedName("user_id") val id: Long,
        val reputation: Long)