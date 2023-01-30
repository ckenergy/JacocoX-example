package com.ckenergy.jacocox

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by chengkai on 2023/1/19.
 */
class MainActivity2: AppCompatActivity() {

    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("MainActivity2", "onCreate")
        handler.postDelayed({ doSome() }, 2000)
    }

    fun doSome() {
        Log.d("MainActivity2", "onCreate")
    }

}