package com.example.sarang.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridItemDecorator(val space: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.top = space
        outRect.left = if (parent.getChildLayoutPosition(view) == 0) { 0 } else { space }
        outRect.right = if (parent.getChildLayoutPosition(view)/3 == 2) { 0 } else { space }
        outRect.bottom = space

    }
}