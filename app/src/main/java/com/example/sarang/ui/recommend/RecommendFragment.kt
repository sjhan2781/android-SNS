package com.example.sarang.ui.recommend

import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.sarang.R
import com.example.sarang.data.model.User
import com.example.sarang.databinding.FragmentRecommendBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class RecommendFragment:BottomSheetDialogFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding
                = DataBindingUtil.inflate<FragmentRecommendBinding>(inflater,
            R.layout.fragment_recommend, container, false)

        val myInfo = arguments?.getParcelable<User>("myInfo")

        binding.lifecycleOwner = this

        binding.myInfo = myInfo


        val list = ArrayList<User>()

        val defaultUri = "android.resource://com.example.koreanologysns/drawable/"

        list.add(User("Bill Gates", Uri.parse(defaultUri + "bill_gates")))
        list.add(User("Elon Musk", Uri.parse(defaultUri + "elon_musk")))
        list.add(User("Son Masayoshi", Uri.parse(defaultUri + "son_masayoshi")))
        list.add(User("Tim Cook", Uri.parse(defaultUri + "tim_cook")))

        val adapter = RecommendListAdapter()
        adapter.setList(list)
        binding.recommendRecyclerViewRecommendList.adapter = adapter

        binding.recommendEditTextSearch.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                adapter.filter.filter(p0.toString())
            }
        })

        return binding.root
    }


}