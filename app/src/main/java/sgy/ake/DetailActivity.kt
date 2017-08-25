package sgy.ake

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function3
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import sgy.ake.adapter.DetailAdapter
import sgy.ake.model.CommonResult
import sgy.ake.model.DetailModel
import sgy.ake.model.Question
import sgy.ake.network.ApiHelper

/**
 * Created by shiguiyou on 2017/8/24.
 */
class DetailActivity : AppCompatActivity() {

    private var mProfile = ""
    private var mUserName = ""
    private var mUserId: Long = 0L
    private lateinit var adapter: DetailAdapter
    private var dialog: Dialog? = null

    companion object {
        val EXTRA_PROFILE = "user_profile"
        val EXTRA_NAME = "user_name"
        val EXTRA_ID = "user_id"

        fun start(context: Context, userProfile: String, userName: String, userId: Long) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(EXTRA_PROFILE, userProfile)
            intent.putExtra(EXTRA_NAME, userName)
            intent.putExtra(EXTRA_ID, userId)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mProfile = intent.getStringExtra(EXTRA_PROFILE)
        mUserName = intent.getStringExtra(EXTRA_NAME)
        mUserId = intent.getLongExtra(EXTRA_ID, 0)

        adapter = DetailAdapter(mUserName, mProfile)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        dialog = WeiboDialogUtils.createLoadingDialog(this, "加载中")
        Single.zip(
                ApiHelper.getService().getQuestionsByUser(mUserId),
                getAnswers(),
                ApiHelper.getService().getFavoritesByUser(mUserId),
                Function3<CommonResult<Question>, List<Question>, CommonResult<Question>, DetailModel>
                { questionResult, answers, favoritesResult ->
                    val questions = questionResult.items.take(3)
                    val favorites = favoritesResult.items.take(3)
                    DetailModel(questions, answers, favorites)
                }
        ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    WeiboDialogUtils.closeDialog(dialog)
                    adapter.setData(it)
                }, {
                    WeiboDialogUtils.closeDialog(dialog)
                    it.printStackTrace()
                    toast("请求错误：${it.message}")
                })
    }

    private fun getAnswers(): Single<List<Question>> {
        return ApiHelper.getService()
                .getAnswersByUser(mUserId)
                .flatMap {
                    val processedAnswers = it.items
                            .filter { it.accept }
                            .take(3)

                    val ids = processedAnswers
                            .map { it.questionId.toString() }
                            .joinToString(separator = ";")

                    ApiHelper.getService()
                            .getQuestionById(ids)
                            .map { it.items }
                }
    }

//    val ids=it.items.filter { it.accept }
//            .take(3)
//            .map { it.questionId.toString() }
//            .joinToString(separator = ";")
//
//    ApiHelper.getService().getQuestionById(ids)
//    .map{}
}