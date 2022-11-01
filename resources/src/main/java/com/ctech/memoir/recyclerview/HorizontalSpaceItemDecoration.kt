package com.ctech.memoir.recyclerview

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class HorizontalSpaceItemDecoration(private val dividedViewHolderClass: Class<out Any>, private val dividerHeight: Int) : RecyclerView.ItemDecoration() {

    override
    fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val viewHolder = parent.getChildViewHolder(view)
        if (viewHolder.javaClass == dividedViewHolderClass) {
            outRect.right = dividerHeight
        }
    }
}