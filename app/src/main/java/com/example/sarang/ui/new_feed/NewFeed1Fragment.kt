package com.example.sarang.ui.new_feed

import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.GradientDrawable
import android.net.Uri
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.sarang.R
import com.example.sarang.databinding.FragmentNewFeed1Binding
import com.example.sarang.ui.new_feed.adapter.GalleryGridAdapter
import com.example.sarang.ui.new_feed.viewmodel.NewFeedViewModel
import kotlinx.android.synthetic.main.activity_main.*

/**
 * A simple [Fragment] subclass.
 */
class NewFeed1Fragment : Fragment() {
    companion object {
        const val REQUEST_READ_EX: Int = 1
    }

    val imgURLList = ArrayList<Uri>()
    private lateinit var binding: FragmentNewFeed1Binding
    private lateinit var newFeedViewModel: NewFeedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        activity.run {
//            toolBar_button?.setOnClickListener {
//                goToNextFragment()
//            }
//        }

        newFeedViewModel = ViewModelProvider(
            this).get(NewFeedViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater
            , R.layout.fragment_new_feed1, container, false
        )

        binding.lifecycleOwner = this

        binding.newFeedViewModel = newFeedViewModel
        val displayMetrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)

        val imageSize = displayMetrics.run {widthPixels/3 - 2*density}.toInt()
        newFeedViewModel.gridAdapter = GalleryGridAdapter(imageSize)
        binding.gridViewNewFeedGallary.adapter = newFeedViewModel.gridAdapter

        binding.gridViewNewFeedGallary.setOnItemClickListener { _, _, i, _ ->
            newFeedViewModel.setSelectedItem(i)
        }

        selfCheckPermission()

        activity?.toolBar_button?.setOnClickListener {
            goToNextFragment()
        }

        return binding.root
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_READ_EX -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    newFeedViewModel.loadGallery(requireContext())
                } else {
                    findNavController().navigateUp()
                }
                return
            }

        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun selfCheckPermission() {
        val permissionCheck =
            ContextCompat.checkSelfPermission(
                requireActivity(),
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            )

        this.activity?.let {
            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                 requestPermissions(
                     arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_READ_EX
                )
            } else {
                newFeedViewModel.loadGallery(requireContext())
            }
        }
    }

    private fun goToNextFragment() {
        val bitmapDrawable = binding.imageViewNewFeedSelected.drawable as? BitmapDrawable
        val gradientDrawable = binding.imageViewNewFeedSelected.drawable as? GradientDrawable
        var bitmap:Bitmap? = null
        val selectedUri = newFeedViewModel.selectedUri.value

        if (bitmapDrawable != null){
            bitmap = bitmapDrawable.bitmap
        }

        val action = NewFeed1FragmentDirections
            .actionMenuNewFeed1ToNewFeed2Fragment(bitmap, selectedUri)

        findNavController().navigate(action)
    }


}
