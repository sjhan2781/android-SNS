package com.example.sarang.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sarang.R
import com.example.sarang.data.model.Feed
import com.example.sarang.data.model.User
import com.example.sarang.databinding.ItemFeedBinding
import com.example.sarang.util.BaseRecyclerAdapter

class FeedRecyclerAdapter(
    val myInfo: User,
    val commentClickListener: (edit:EditText)->Unit,
    val recommendClickListener: () -> Unit,
    val bookmarkClickListener: (feed:Feed) -> Unit)
    : RecyclerView.Adapter<FeedRecyclerAdapter.FeedViewHolder>(),
    BaseRecyclerAdapter{
    var feedList = ArrayList<Feed>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        return FeedViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),R.layout.item_feed,
                parent, false))
    }

    override fun getItemCount(): Int {
        return feedList.size
    }

    override fun getItemId(position: Int): Long {
        return feedList[position].hashCode().toLong()
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        holder.bind(feedList[position])
    }

    override fun setItems(items: ArrayList<*>){
        feedList = items as ArrayList<Feed>
        notifyDataSetChanged()
    }

    inner class FeedViewHolder(private val binding:ItemFeedBinding):
        RecyclerView.ViewHolder(binding.root){

        fun bind(item: Feed){
            binding.feedItem = item
            binding.myInfo = myInfo

            binding.imageBtnComment.setOnClickListener {
                commentClickListener.invoke(binding.editTextComment)
            }
            binding.imageBtnRecommend.setOnClickListener {
                recommendClickListener.invoke()
            }
            binding.imageBtnBookmark.run {
                setOnClickListener {
                    item.toggleBookmarked()
                    bookmarkClickListener.invoke(item)
                    binding.imageBtnBookmark.isSelected = item.bookmarked
                }
                binding.imageBtnBookmark.isSelected = item.bookmarked
            }
        }
    }
}