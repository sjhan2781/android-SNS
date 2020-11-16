package com.example.sarang.ui.mypage

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.sarang.R
import com.example.sarang.databinding.FragmentMypage1Binding
import com.example.sarang.ui.main.viewmodel.MainViewModel
import com.example.sarang.ui.mypage.adapter.MyFeedGridAdapter

class MyPage1Fragment : Fragment() {
    lateinit var adapter: MyFeedGridAdapter
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentMypage1Binding>(inflater
            , R.layout.fragment_mypage1, container, false)

        binding.lifecycleOwner = this

        binding.imageBtnMypageSettings.setOnClickListener {
            findNavController().navigate(R.id.action_menu_mypage_to_settingFragment)
        }

        val displayMetrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)
        val imageSize = displayMetrics.run {widthPixels/3 - 2*density}.toInt()

        adapter = MyFeedGridAdapter(imageSize)
        binding.gridMypageFeed.adapter = adapter

        adapter.setItems(getDatas())

        return binding.root
    }

    fun getDatas():ArrayList<String>{
        val list = ArrayList<String>()

        for (i in 0..120) {
            val id = i
            list.add("https://i.picsum.photos/id/$id/200/200.jpg")
        }

        return list
    }

}
