package com.example.sarang.ui.new_feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.sarang.R
import com.example.sarang.data.model.Feed
import com.example.sarang.ui.main.viewmodel.MainViewModel
import com.example.sarang.databinding.FragmentNewFeed2Binding
import kotlinx.android.synthetic.main.activity_main.*


class NewFeed2Fragment : Fragment() {
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = activity?.run {
//            ViewModelProviders.of(this)[MainViewModel::class.java]
            ViewModelProvider(this).get(MainViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

//        mainViewModel.getFeedData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentNewFeed2Binding>(inflater
            , R.layout.fragment_new_feed2, container, false)

        val safeArgs = NewFeed2FragmentArgs.fromBundle(requireArguments())
        val bitmap = safeArgs.selectedImage
        val selectedUri = safeArgs.imageUri

        binding.lifecycleOwner = this
        binding.selectedBitmap = bitmap
        binding.mainViewModel = mainViewModel

        requireActivity().toolBar_button.setOnClickListener {
            val feed = Feed(mainViewModel.myInfo.value!!,"제목", binding.editTextNewFeed2Content.text.toString(), selectedUri)
            mainViewModel.addFeed(feed)
            findNavController().navigate(R.id.action_newFeed2Fragment_to_menu_home)
        }

        return binding.root
    }
}
