package com.ctech.common.view

import android.widget.SeekBar
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow


fun TextView.afterTextChange(): Flow<String> {

    return callbackFlow {

        addTextChangedListener(afterTextChanged = {
            trySend(it!!.toString())
        })

        awaitClose {
            cancel()
        }
    }

}

fun SeekBar.onChange(): Flow<Int> {

    return callbackFlow {

        setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                trySend(seekBar.progress)
            }

        })

        awaitClose {
            cancel()
        }

    }

}