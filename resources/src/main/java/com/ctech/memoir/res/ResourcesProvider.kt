package com.ctech.memoir.res

import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes

interface ResourcesProvider  {

    fun getString(@StringRes id: Int): String

    fun getString(@StringRes id: Int, vararg args: Any): String

    fun getPlurals(@PluralsRes id: Int, quantity: Int): String

    fun getPackageName(): String

    fun getColor(@ColorRes id: Int): Int

    fun getDimens(@DimenRes id: Int): Int

    fun getFontSize(value: Float): Float

}