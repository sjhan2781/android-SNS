package com.example.sarang.ui.mypage.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.example.sarang.R

class MyFeedGridAdapter(val imageSize: Int):BaseAdapter() {
    var feedList = ArrayList<String>()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val imageView: ImageView?

        imageView = if (convertView == null) {
            LayoutInflater.from(parent?.context)
                .inflate(R.layout.item_thumbnail, parent, false)
                .findViewById(R.id.imageView_item_thumbnail) as ImageView
//            ImageView(parent?.context)

        } else {
            convertView as ImageView
        }

        imageView.scaleType = ImageView.ScaleType.FIT_XY

        val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams(imageSize, imageSize))
        imageView.layoutParams = params

        parent?.context?.let {
            Glide.with(it)
                .load(feedList[position])
                .placeholder(R.drawable.loading)
                .fitCenter()
                .dontAnimate()
                .into(imageView)
        }

        return imageView
    }

    override fun getItem(p0: Int): Any {
        return feedList[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return feedList.size
    }

    fun setItems(itemList:ArrayList<String>){
        feedList = itemList
        notifyDataSetChanged()
    }
}