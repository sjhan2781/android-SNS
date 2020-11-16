package com.example.sarang.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.sarang.R
import com.example.sarang.data.model.Feed
import com.example.sarang.databinding.FragmentHomeBinding
import com.example.sarang.ui.home.adapter.FeedRecyclerAdapter
import com.example.sarang.ui.main.viewmodel.MainViewModel


class HomeFragment() : Fragment(), SwipeRefreshLayout.OnRefreshListener {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding:FragmentHomeBinding

    private fun focusToEditText(editText: EditText){
        editText.requestFocus()
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(editText,0)
    }

    private fun openFollowingList(){
//        val bundle = bundleOf("myInfo" to  MyInfo("한상진"))
//        this.findNavController().navigate(R.id.action_homeFragment_to_bottomSheet, bundle)
        val action = HomeFragmentDirections.actionHomeFragmentToBottomSheet( mainViewModel.myInfo.value!! )

        this.findNavController().navigate(action)
    }

    private fun addBookmark(feed: Feed){

        if(feed.bookmarked) {
            mainViewModel.addBookmark(feed)
            Toast.makeText(this.requireContext(), "저장되었습니다", Toast.LENGTH_SHORT).show()
        }
        else{
            mainViewModel.removeBookmark(feed)
            Toast.makeText(this.requireContext(), "삭제되었습니다. ${feed.title}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = activity?.run {
            ViewModelProvider(this).get(MainViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
        ): View? {

        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_home, container, false)

        binding.lifecycleOwner = this

        binding.mainViewModel = mainViewModel

        val adapter = FeedRecyclerAdapter(mainViewModel.myInfo.value!!,
            {focusToEditText(it)}, {openFollowingList()}, {addBookmark(it)})
        adapter.setHasStableIds(true)
        binding.recyclerViewHome.adapter = adapter

        binding.swipeRefreshLayoutHome.setOnRefreshListener(this)

        return binding.root
    }

    override fun onRefresh() {
        mainViewModel.addFeedData()

        binding.swipeRefreshLayoutHome.isRefreshing = false
    }
}
