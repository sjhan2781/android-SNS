package com.example.sarang.ui.new_feed.viewmodel

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sarang.ui.new_feed.adapter.GalleryGridAdapter

class NewFeedViewModel() : ViewModel() {

    val imgURLList = ArrayList<Uri>()
    lateinit var gridAdapter: GalleryGridAdapter
    var selectedUri:MutableLiveData<Uri> = MutableLiveData<Uri>()

    init {
        //        selectedUri.postValue(Uri.parse("content://media/external/images/media/32"))
        selectedUri.value = Uri.EMPTY
    }

    fun loadGallery(context: Context){
        val projection = arrayOf(MediaStore.Images.Media._ID)
        val uriExternal: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI

        val cursor = context.contentResolver!!.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection,
            null,
            null,
            MediaStore.Images.ImageColumns.DATE_TAKEN + " DESC"
        )


        if (cursor != null) {
            while (cursor.moveToNext()) {
                val id= cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID))
                val uri = Uri.withAppendedPath(uriExternal, "" + id)
                imgURLList.add(uri)
//                Log.e("File Path", uri.toString())
            }
            cursor.close()
            gridAdapter.setList(imgURLList)
        }

        if (imgURLList.isNotEmpty()){
            setSelectedItem(0)
        }
    }

    fun setSelectedItem(position:Int){
        gridAdapter.setSelectedItem(position)
        selectedUri.value = gridAdapter.imgList[position]
    }
}