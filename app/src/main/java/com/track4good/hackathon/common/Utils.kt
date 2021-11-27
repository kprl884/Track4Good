package com.track4good.hackathon.common

import android.os.Handler

class Utils {
    companion object {
        fun after(delay: Long, process: () -> Unit) {
            Handler().postDelayed({
                process()
            }, delay)
        }
    }
}