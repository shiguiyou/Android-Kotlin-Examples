package sgy.ake

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.view_item.view.*
import sgy.ake.bean.Item

/**
 * Created by shiguiyou on 2018/2/28.
 */
class MainItemAdapter : RecyclerView.Adapter<MainItemAdapter.ItemViewHolder>() {

    var items: List<Item>? = null

    public fun setData(data: List<Item>) {
        items = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MainItemAdapter.ItemViewHolder {
        return ItemViewHolder(parent!!.inflate(R.layout.view_item))
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    override fun onBindViewHolder(holder: MainItemAdapter.ItemViewHolder?, position: Int) {
        holder?.tvTitle?.text = items?.get(position)?.title
        holder?.tvSub?.text="作者:${items?.get(position)?.author}  分类:${items?.get(position)?.chapterName}  时间:${items?.get(position)?.niceDate}"
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle = itemView.item_title
        val tvSub = itemView.item_sub
    }
}