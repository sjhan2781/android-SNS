package com.example.sarang.data.model

import android.os.Parcel
import android.os.Parcelable

data class Bookmark(val title: String, val publisher: String, val time: String) : Parcelable {
    constructor(source: Parcel) : this(
        source.readString() ?: "",
        source.readString() ?: "",
        source.readString() ?: ""
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(title)
        writeString(publisher)
        writeString(time)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Bookmark> = object : Parcelable.Creator<Bookmark> {
            override fun createFromParcel(source: Parcel): Bookmark = Bookmark(source)
            override fun newArray(size: Int): Array<Bookmark?> = arrayOfNulls(size)
        }
    }
}