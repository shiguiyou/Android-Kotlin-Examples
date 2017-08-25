package sgy.ake.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_user.view.*
import sgy.ake.DetailActivity
import sgy.ake.R
import sgy.ake.inflate
import sgy.ake.loadUrl
import sgy.ake.model.User

/**
 * Created by shiguiyou on 2017/8/24.
 */
class UserListAdapter(ctx: Context) : RecyclerView.Adapter<UserListAdapter.UserListVH>() {

    private var list: List<User>? = null
    private val context = ctx

    fun setData(users: List<User>) {
        list = users
        notifyDataSetChanged()
    }

    override fun getItemCount() = list?.size ?: 0

    override fun onBindViewHolder(holder: UserListVH, position: Int) {
        val user = list!![position]

        holder.ivProfile.loadUrl(user.avatar)
        holder.tvUserName.text = user.name
        holder.tvUserRep.text = user.reputation.toString()

        holder.itemView.setOnClickListener { DetailActivity.start(context, user.avatar, user.name, user.id) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UserListVH(parent.inflate(R.layout.item_user))

    class UserListVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivProfile = itemView.iv_profile
        val tvUserName = itemView.tv_name
        val tvUserRep = itemView.tv_rep
    }
}