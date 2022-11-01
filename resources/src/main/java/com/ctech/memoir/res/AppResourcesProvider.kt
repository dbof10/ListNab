package com.ctech.memoir.res

import android.content.Context
import android.util.TypedValue
import androidx.core.content.ContextCompat

class AppResourcesProvider(private val context: Context) : ResourcesProvider {


    override fun getColor(id: Int): Int {
        return ContextCompat.getColor(context, id)
    }

    override fun getPackageName(): String {
        return context.packageName
    }


    override fun getString(id: Int): String {
        return context.getString(id)
    }


    override fun getString(id: Int, vararg args: Any): String {
        return context.getString(id, *args)
    }

    override fun getPlurals(id: Int, quantity: Int): String {
        return context.resources.getQuantityString(id, quantity, quantity)
    }

    override fun getDimens(id: Int): Int {
        return context.resources.getDimensionPixelSize(id)
    }

    override fun getFontSize(value: Float): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, value, context.resources.displayMetrics)
    }
}