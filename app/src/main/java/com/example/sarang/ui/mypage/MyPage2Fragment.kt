package com.example.sarang.ui.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.sarang.R
import com.example.sarang.databinding.FragmentMypage2Binding
import kotlinx.android.synthetic.main.activity_main.*


class MyPage2Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentMypage2Binding>(inflater
            , R.layout.fragment_mypage2, container, false)

        requireActivity().toolBar_button.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }

}
