package com.randomnumber.core.base.ui

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class VerticalSpaceItemDecorator(private val verticalSpaceHeight: Int) :
    ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        if (position != RecyclerView.NO_POSITION && position != 0) {
            outRect.top = verticalSpaceHeight
        }
        if (position != RecyclerView.NO_POSITION && position != parent.adapter!!.itemCount - 1) {
            outRect.bottom = verticalSpaceHeight
        }
    }
}