
package com.ctech.memoir.widget

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.ctech.memoir.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoadingTextView : AppCompatTextView {
    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}


    protected val viewLifecycleScope = CoroutineScope(
        Job() + Dispatchers.Main
    )


    private var running = true

    private var counter = 0

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        viewLifecycleScope.launch {
            while (running) {

                when {
                    counter % 4 == 0 -> {
                        setText(R.string.label_loading_1)
                    }
                    counter % 4 == 1 -> {
                        setText(R.string.label_loading_2)
                    }
                    counter % 4 == 2 -> {
                        setText(R.string.label_loading_3)
                    }
                    counter % 4 == 3 -> {
                        setText(R.string.label_loading_4)
                    }
                }
                counter++
                delay(1000)
            }
        }
    }


    override fun onDetachedFromWindow() {
        running = false
        viewLifecycleScope.cancel()
        super.onDetachedFromWindow()
    }
}