package com.example.sarang.custom

import androidx.lifecycle.MutableLiveData

class MutableLiveArray<T>: MutableLiveData<ArrayList<T>>() {

    init {
        value = ArrayList()
    }

    fun add(item: T){
        val items = value
        items?.add(item)
        value = items
    }

    fun remove(item: T){
        val items = value
        items?.remove(item)
        value = items
    }

}