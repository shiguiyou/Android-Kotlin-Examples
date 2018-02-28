package sgy.ake

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * 扩展函数
 * Created by shiguiyou on 2018/2/28.
 */
fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}