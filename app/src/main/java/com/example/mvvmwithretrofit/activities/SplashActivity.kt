package com.example.mvvmwithretrofit.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mvvmwithretrofit.R
import com.example.mvvmwithretrofit.MainActivity

import android.content.Intent
import android.os.Handler


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            val mIntent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(mIntent)
            finish()
        }, 2000)
    }
}