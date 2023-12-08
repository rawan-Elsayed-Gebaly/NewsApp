package com.example.newsapp_v2.ui.home.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.newsapp_v2.R
import com.example.newsapp_v2.ui.home.HomeActivity

class Splash:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash)
        startMainActivity()
    }

    private fun startMainActivity() {
        Handler(Looper.getMainLooper()).postDelayed({
         val intent = Intent(this@Splash , HomeActivity::class.java)
            startActivity(intent) }
            , 2000 )
        finish()

    }

}