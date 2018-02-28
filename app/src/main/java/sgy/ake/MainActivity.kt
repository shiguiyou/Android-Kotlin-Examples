package sgy.ake

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import sgy.ake.bean.Article
import sgy.ake.bean.HttpResult

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var adapter=MainItemAdapter()
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapter

        HttpHelper.getApi().getArticles()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<HttpResult<Article>> {
                    override fun onComplete() {
                        Log.i("square", "onComplete")
                    }

                    override fun onSubscribe(d: Disposable) {
                        Log.i("square", "onSubscribe")
                    }

                    override fun onNext(t: HttpResult<Article>) {
                        Log.i("square", "onNext ${t.data.datas.size}")
                        Log.i("square", "onNext ${t.data.datas[1].title}")

                        adapter.setData(t.data.datas)
                    }

                    override fun onError(e: Throwable) {
                        Log.i("square", "onError $e.message")
                    }

                })
    }

}
