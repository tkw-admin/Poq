package com.tecknoworks.poq.log

import android.util.Log

/**
 * Created by Mihai Ionescu on 2020-02-11.
 */
object PoqLog {

    fun logEvent(event: String) {
        Log.d("PoqEvent", event)
    }

}