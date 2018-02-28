package sgy.ake.bean

data class Item(val apkLink: String,
                val author: String,
                val chapterId: Int,
                val chapterName: String,
                val collect: Boolean,
                val courseId: Int,
                val desc: String,
                val envelopePic: String,
                val id: Int,
                val link: String,
                val niceDate: String,
                val origin: String,
                val projectLink: String,
                val publishTime: Long,
                val title: String,
                val visible: Int,
                val zan: Int)

/* 示例
{
"apkLink": "",
"author": "xiaoyang",
"chapterId": 292,
"chapterName": "pdf电子书",
"collect": false,
"courseId": 13,
"desc": "",
"envelopePic": "",
"id": 2421,
"link": "http://www.wanandroid.com/blog/show/2057",
"niceDate": "14小时前",
"origin": "",
"projectLink": "",
"publishTime": 1519745961000,
"title": "2018年四大热门技术岗位跳槽指南 pdf",
"visible": 1,
"zan": 0
}
*/