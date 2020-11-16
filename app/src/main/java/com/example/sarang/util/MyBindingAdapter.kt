package com.example.sarang.util

import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.module.AppGlideModule
import com.example.sarang.R

object MyBindingAdapter {
    @JvmStatic
    @BindingAdapter("imageUri")
    fun setImageUri(imageView: ImageView, uri: Uri?) {
        Log.d("ASDASD", "$uri")
        GlideApp.with(imageView)
            .load(uri)
            .fitCenter()
            .placeholder(R.drawable.loading)
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("circleImageUri")
    fun setCircleImageUri(imageView: ImageView, uri: Uri?) {
        GlideApp.with(imageView)
            .load(uri)
            .circleCrop()
            .placeholder(R.drawable.loading_circle)
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("imageBitmap")
    fun setImageBitmap(imageView: ImageView, bitmap: Bitmap?) {
        GlideApp.with(imageView)
            .load(bitmap)
            .circleCrop()
            .placeholder(R.drawable.loading)
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("listItems")
    fun setListItems(recyclerView: RecyclerView, listItems:ArrayList<*>){
        val adapter = recyclerView.adapter as BaseRecyclerAdapter

        adapter.setItems(listItems)
    }
}


@GlideModule
class MyAppGlideModule: AppGlideModule() {
}
