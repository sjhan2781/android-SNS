package com.example.sarang.ui.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.sarang.R
import com.example.sarang.databinding.FragmentBookmarkBinding
import com.example.sarang.ui.bookmark.adapter.BookmarkRecyclerAdapter
import com.example.sarang.ui.main.viewmodel.MainViewModel


class BookmarkFragment : Fragment() {
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = activity?.run {
//            ViewModelProviders.of(this)[MainViewModel::class.java]
            ViewModelProvider(this).get(MainViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding
                = DataBindingUtil.inflate<FragmentBookmarkBinding>(inflater, R.layout.fragment_bookmark, container, false)

        binding.mainViewModel = mainViewModel

        val adapter = BookmarkRecyclerAdapter()
        adapter.setHasStableIds(true)
        binding.recyclerViewBookmark.adapter = adapter

        return binding.root
    }

}
