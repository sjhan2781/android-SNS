package com.example.sarang.ui.main.viewmodel

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sarang.custom.MutableLiveArray
import com.example.sarang.data.model.Feed
import com.example.sarang.data.model.User
import com.example.sarang.ui.home.adapter.FeedRecyclerAdapter

class MainViewModel() : ViewModel() {
    val homeFeedList = MutableLiveArray<Feed>()
    val bookmarkList = MutableLiveArray<Feed>()
    val newsList = MutableLiveArray<Feed>()
    var homeRecyclerAdapter: FeedRecyclerAdapter? = null
    val myInfo: MutableLiveData<User> = MutableLiveData<User>()

    init {
//        var uri: Uri = Uri.parse("android.resource://com.example.koreanologysns/drawable/profile_test")
//        uri = Uri.parse("content://media/external/images/media/34")
        var drawableUri = "android.resource://com.example.koreanologysns/drawable/"
        var feed =   Feed(
            User("이경우", Uri.parse(drawableUri + "chairman")),
            "세종 리더십", "황희 정인지 김종서 이순지 장영실 '어벤져스 정부'",
            Uri.parse(drawableUri + "great_king")
        )

        homeFeedList.add(feed)
        bookmarkList.add(feed)
        newsList.add(feed)

        feed = Feed(
            User("Elon Musk", Uri.parse(drawableUri + "elon_musk")),
            "조선 잔칫상에서 가장 비싼 음식은?", "내용내용내용내용",
            Uri.parse(drawableUri + "feed2")
        )

        homeFeedList.add(feed)
        bookmarkList.add(feed)
        newsList.add(feed)

        myInfo.value = User(
            "이경우",
            Uri.parse(drawableUri + "chairman")
        )
    }

    fun setFeedAdapter() {

        homeRecyclerAdapter!!.feedList = homeFeedList.value!!
        homeRecyclerAdapter!!.setHasStableIds(true)

    }

    fun getFeedData(): ArrayList<Feed> {
        return homeFeedList.value!!
    }

    fun addFeedData() {
        val uri: Uri =
            Uri.parse("android.resource://com.example.koreanologysns/drawable/profile_test")

//        homeFeedList.add(Feed("이경우", "제목 추가", "내용내용내용내용", uri))
    }

    fun addFeed(feed: Feed) {
        homeFeedList.add(feed)
    }

    fun addBookmark(feed: Feed) {
        bookmarkList.add(feed)
    }

    fun removeBookmark(feed: Feed) {
        bookmarkList.remove(feed)
    }
}