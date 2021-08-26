package com.example.demo

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity(R.layout.activity_splash) {

    companion object{
        private const val SHOW_TIME = 100L
    }

    private val myHandler = Handler(Looper.myLooper()!!)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myHandler.postDelayed({
            startActivity(Intent(MainActivity.intent(this@SplashActivity)))
            finish()
        }, SHOW_TIME)
    }

    override fun onDestroy() {
        myHandler.removeCallbacksAndMessages(null)
        super.onDestroy()
    }
}