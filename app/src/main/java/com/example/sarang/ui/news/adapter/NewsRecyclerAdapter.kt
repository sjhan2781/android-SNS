package com.example.sarang.ui.news.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sarang.R
import com.example.sarang.data.model.Feed
import com.example.sarang.databinding.ItemNewsBinding
import com.example.sarang.util.BaseRecyclerAdapter
import com.example.sarang.util.RecyclerItemDecorator

class NewsRecyclerAdapter : RecyclerView.Adapter<NewsRecyclerAdapter.NewsViewHolder>(), BaseRecyclerAdapter {
    var newsList = ArrayList<Feed>()

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        recyclerView.addItemDecoration(
            RecyclerItemDecorator(
                recyclerView.resources.getDimensionPixelOffset(R.dimen.recyclerView_news_spacing)
            )
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context)
                , R.layout.item_news, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun getItemId(position: Int): Long {
        return newsList[position].hashCode().toLong()
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(newsList[position])
    }

    override fun setItems(items: ArrayList<*>) {
        newsList = items as ArrayList<Feed>
        notifyDataSetChanged()
    }

    inner class NewsViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Feed) {
            binding.newsItem = item
        }
    }
}