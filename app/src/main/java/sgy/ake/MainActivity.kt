package sgy.ake

import android.app.Dialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import sgy.ake.adapter.UserListAdapter
import sgy.ake.network.ApiHelper

class MainActivity : AppCompatActivity() {

    private val adapter = UserListAdapter(this)
    private var dialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        dialog = WeiboDialogUtils.createLoadingDialog(this, "加载中")
        ApiHelper.getService().getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    WeiboDialogUtils.closeDialog(dialog)
                    adapter.setData(it.items)
                }, {
                    WeiboDialogUtils.closeDialog(dialog)
                    it.printStackTrace()
                    toast("请求错误：${it.message}")
                })
    }

}
