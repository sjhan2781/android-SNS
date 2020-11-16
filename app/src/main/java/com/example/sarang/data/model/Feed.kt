package com.example.sarang.data.model

import android.net.Uri
import android.os.Parcel
import android.os.Parcelable

data class Feed(val author: User, val title: String, val content: String, val thumbnailUri: Uri?) :
    Parcelable {
    var bookmarked: Boolean = false

    fun toggleBookmarked() {
        this.bookmarked = !this.bookmarked
    }

    constructor(source: Parcel) : this(
    source.readParcelable<User>(User::class.java.classLoader) ?: User("이름", null),
    source.readString() ?: "",
    source.readString() ?: "",
    source.readParcelable<Uri>(Uri::class.java.classLoader)
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeParcelable(author, 0)
        writeString(title)
        writeString(content)
        writeParcelable(thumbnailUri, 0)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Feed> = object : Parcelable.Creator<Feed> {
            override fun createFromParcel(source: Parcel): Feed = Feed(source)
            override fun newArray(size: Int): Array<Feed?> = arrayOfNulls(size)
        }
    }
}