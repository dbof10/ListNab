package com.ctech.memoir.utils

import android.content.Context
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.ViewHolder.getColor(@ColorRes colorRes: Int): Int {
    val context = itemView.context
    return ContextCompat.getColor(context, colorRes)
}

val RecyclerView.ViewHolder.context : Context get() {
   return itemView.context
}