package com.example.sarang.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.sarang.R
import com.example.sarang.databinding.FragmentNewsBinding
import com.example.sarang.ui.main.viewmodel.MainViewModel
import com.example.sarang.ui.news.adapter.NewsRecyclerAdapter

/**
 * A simple [Fragment] subclass.
 */
class NewsFragment : Fragment() {
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentNewsBinding>(inflater
            , R.layout.fragment_news, container, false)

        binding.lifecycleOwner = this

        binding.mainViewModel = mainViewModel

        val adapter = NewsRecyclerAdapter()
        adapter.setHasStableIds(true)
        binding.recyclerViewNews.adapter = adapter

        return binding.root
    }

}
