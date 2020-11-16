package com.example.sarang.data.model

import android.net.Uri
import android.os.Parcel
import android.os.Parcelable

class User(val name: String, val profileUri: Uri?) : Parcelable {
    constructor(source: Parcel) : this(
    source.readString() ?: "",
    source.readParcelable<Uri>(Uri::class.java.classLoader)
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(name)
        writeParcelable(profileUri, 0)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<User> = object : Parcelable.Creator<User> {
            override fun createFromParcel(source: Parcel): User = User(source)
            override fun newArray(size: Int): Array<User?> = arrayOfNulls(size)
        }
    }
}