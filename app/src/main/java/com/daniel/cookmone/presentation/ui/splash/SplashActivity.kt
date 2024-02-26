package com.daniel.cookmone.presentation.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.daniel.cookmone.R
import com.daniel.cookmone.presentation.ui.MainActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

       Handler().postDelayed(kotlinx.coroutines.Runnable {
           val i = Intent(this@SplashActivity, MainActivity::class.java)
           startActivity(i)

           finish()
       }, 1000)
    }
}