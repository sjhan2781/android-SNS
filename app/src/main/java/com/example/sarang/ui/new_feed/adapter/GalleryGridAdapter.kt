package com.example.sarang.ui.new_feed.adapter

import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.res.ResourcesCompat
import com.bumptech.glide.Glide
import com.example.sarang.R


class GalleryGridAdapter(val imageSize:Int) : BaseAdapter() {
    var imgList = ArrayList<Uri>()
    var selectedIndex: Int? = null

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val imageView: ImageView?
        val foreground: Drawable? = if (position == selectedIndex) {
            parent?.resources?.let { ResourcesCompat.getDrawable(it, R.drawable.selected_item, null) }
        } else {
            null
        }

        imageView = if (convertView == null) {
            ImageView(parent?.context)
        } else {
            convertView as ImageView
        }

        val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams(imageSize, imageSize))
        imageView.layoutParams = params

        parent?.context?.let {
            Glide.with(it).load(imgList[position])
                .fitCenter()
                .placeholder(R.drawable.loading)
                .into(imageView)
        }

        imageView.scaleType = ImageView.ScaleType.FIT_XY

        imageView.foreground = foreground

        return imageView
    }


    override fun getItem(p0: Int): Any {
        return imgList[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return imgList.size
    }

    fun setList(list: ArrayList<Uri>) {
        imgList = list
        notifyDataSetChanged()
    }

    fun setSelectedItem(position: Int) {
        selectedIndex = position
        notifyDataSetChanged()
    }
}