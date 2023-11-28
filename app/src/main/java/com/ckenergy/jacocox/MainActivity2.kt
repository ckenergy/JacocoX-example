package com.ckenergy.jacocox

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.ckenergy.mylibrary.LibClass.show

/**
 * Created by chengkai on 2023/1/19.
 */
class MainActivity2: AppCompatActivity() {

    companion object {

        fun startThis(context1: Context) {
            context1.startActivity(Intent(context1, MainActivity2::class.java))
        }

    }

    var handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("main", "onCreate")
        setContentView(R.layout.activity_main)
        WatchService.open(this)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val list: MutableList<String> = ArrayList()
            list.add("position 1")
            list.add("position 2")
            list.add("position 3")
            doSome(1L, 3.0, 5, "doSome", 2f, false, list, "ss", 1)
        }
        show()
        //        MyClass.INSTANCE.show();
        findViewById<View>(R.id.btn).setOnClickListener { it->
            handler.post { Log.e("main", "post") }
            return@setOnClickListener
        }
        val s = object : View.OnClickListener {
            override fun onClick(v: View) {
                val start = System.currentTimeMillis()
                doSome1()
                Log.e("main", "doSome time11:" + (System.currentTimeMillis() - start))
                handler.post { Log.e("main", "post") }
            }

            inner class ss {
                fun ssTest() {
                    handler.post { Log.e("main1", "ssTest") }
                }
            }
        }

        findViewById<View>(R.id.btn).setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                val start = System.currentTimeMillis()
                doSome1()
                Log.e("main", "doSome time11:" + (System.currentTimeMillis() - start))
                handler.post { Log.e("main", "post") }
            }

            inner class ss {
                fun ssTest() {
                    handler.post { Log.e("main", "ssTest") }
                }
            }
        })
        handler.post {
            handler.post {
                class mm {
                    fun ttTest() {
                        Log.e("main", "11ttTest")
                    }
                }
                Log.e("main", "post1")
            }
            handler.post {
                Log.e("main", "post1")
            }
            Log.e("main", "post1")
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun doSome(
        l: Long,
        d: Double,
        type: Int,
        name: String,
        f: Float,
        su: Boolean,
        list: List<String>,
        vararg args: Any
    ) {
        Log.e("main", "doSome")
    }

    private fun doSome1() {
//        sendBroadcast(new Intent("jacocox.intent.action.generate"));
//        JacocoHelper.INSTANCE.generateEcFile(true, this);
        startThis(this)
        finish()
    }

    fun onClick(v: View?) {
        findViewById<View>(R.id.btn).setOnClickListener {
            Log.e("main", "onClick")
        }
        handler.post {
            class mm {
                fun tt1Test() {
                    finish()
                }
            }
        }
    }

    internal class TestInner {
        fun test() {}
    }

}

fun ttest() {
    val handler = Handler()
    handler.post {
        handler.post {
            class mm {
                fun ttTest() {
                    Log.e("main", "11ttTest")
                }
            }
            Log.e("main", "post1")
        }
        handler.post {
            Log.e("main", "post1")
        }
        Log.e("main", "post1")
    }
}