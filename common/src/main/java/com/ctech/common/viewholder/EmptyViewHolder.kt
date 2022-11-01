package com.ctech.common.viewholder

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class EmptyViewHolder(view: View) : RecyclerView.ViewHolder(view) {


    companion object {
        fun create(parent: ViewGroup): EmptyViewHolder {
            val view = View(parent.context)
            return EmptyViewHolder(view)
        }
    }

}