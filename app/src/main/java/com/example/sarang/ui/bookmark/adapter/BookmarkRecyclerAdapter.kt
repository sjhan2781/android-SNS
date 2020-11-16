package com.example.sarang.ui.bookmark.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sarang.R
import com.example.sarang.data.model.Feed
import com.example.sarang.databinding.ItemBookmarkBinding
import com.example.sarang.util.BaseRecyclerAdapter

class BookmarkRecyclerAdapter:
    RecyclerView.Adapter<BookmarkRecyclerAdapter.BookmarkItemViewHolder>(),
    BaseRecyclerAdapter{
    var bookmarkList = ArrayList<Feed>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkItemViewHolder {
        return BookmarkItemViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_bookmark,
                parent, false))
    }

    override fun getItemCount(): Int {
        return bookmarkList.size
    }

    override fun getItemId(position: Int): Long {
        return bookmarkList[position].hashCode().toLong()
    }

    override fun onBindViewHolder(holder: BookmarkItemViewHolder, position: Int) {
        holder.bind(bookmarkList[position])
    }

    override fun setItems(items: ArrayList<*>) {
        bookmarkList = items as ArrayList<Feed>
        notifyDataSetChanged()
    }

    inner class BookmarkItemViewHolder(private val binding: ItemBookmarkBinding)
        : RecyclerView.ViewHolder(binding.root){

        fun bind(item: Feed){
            binding.feed = item
        }

    }
}