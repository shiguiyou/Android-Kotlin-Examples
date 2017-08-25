package sgy.ake.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_detail.view.*
import kotlinx.android.synthetic.main.item_detail_title.view.*
import kotlinx.android.synthetic.main.item_detail_user.view.*
import sgy.ake.R
import sgy.ake.inflate
import sgy.ake.loadUrl
import sgy.ake.model.DetailModel

/**
 * Created by shiguiyou on 2017/8/24.
 */
class DetailAdapter(name: String, profile: String) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_USER = 0
    private val TYPE_TITLE = 1
    private val TYPE_QUESTION = 2
    private val TYPE_ANSWER = 3
    private val TYPE_FAVORITE = 4

    private var details: DetailModel? = null
    private val userName = name
    private val userProfile = profile

    fun setData(d: DetailModel) {
        details = d
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        if (details != null) {
            return 13
        } else {
            return 0
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return TYPE_USER
        } else if (position in 2..4) {
            return TYPE_QUESTION
        } else if (position in 6..8) {
            return TYPE_ANSWER
        } else if (position in 10..12) {
            return TYPE_FAVORITE
        } else {
            return TYPE_TITLE
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {

        when (getItemViewType(position)) {
            TYPE_USER -> {
                (holder as DetailUserVH).ivProfile.loadUrl(userProfile)
                holder.tvName.text = userName
            }
            TYPE_TITLE -> {
                if (position == 1) {
                    (holder as DetailTitleVH).tvTitle.text = "Top Questions By User"
                } else if (position == 5) {
                    (holder as DetailTitleVH).tvTitle.text = "Top Answers By User"
                } else if (position == 9) {
                    (holder as DetailTitleVH).tvTitle.text = "Favorited By User"
                }
            }
            TYPE_QUESTION -> {
                val ques = details!!.questions[position - 2]
                (holder as DetailVH).tvTitle.text = ques.title
                holder.tvScore.text = "${ques.score} points"
                holder.tvViewed.text = "viewed ${ques.view_count}"
            }
            TYPE_ANSWER -> {
                val ques = details!!.answers[position - 6]
                (holder as DetailVH).tvTitle.text = ques.title
                holder.tvScore.text = "${ques.score} points"
                holder.tvViewed.text = "viewed ${ques.view_count}"
            }
            TYPE_FAVORITE -> {
                val ques = details!!.favorites[position - 10]
                (holder as DetailVH).tvTitle.text = ques.title
                holder.tvScore.text = "${ques.score} points"
                holder.tvViewed.text = "viewed ${ques.view_count}"
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_USER) {
            return DetailUserVH(parent.inflate(R.layout.item_detail_user))
        } else if (viewType == TYPE_TITLE) {
            return DetailTitleVH(parent.inflate(R.layout.item_detail_title))
        } else {
            return DetailVH(parent.inflate(R.layout.item_detail))
        }
    }

    class DetailUserVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName = itemView.tv_name
        val ivProfile = itemView.iv_profile
    }

    class DetailTitleVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle = itemView.tv_title
    }

    class DetailVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle = itemView.tv_qtitle
        val tvScore = itemView.tv_score
        val tvViewed = itemView.tv_viewed
    }

}