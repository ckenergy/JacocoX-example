package com.ckenergy.jacocox

import android.os.Handler
import android.util.Log

/**
 * @author ckenergy
 * @date 2023/2/14
 * @desc
 */
fun cc() {
    val handler = Handler()
    handler.post {
        handler.post {
            class mm {
                fun ttTest() {
                    Log.e("main", "11ttTest")
                }
            }
            Log.e("main", "post11")
        }
        handler.post {
            Log.e("main", "post1")
        }
        Log.e("main", "post1")
    }
}