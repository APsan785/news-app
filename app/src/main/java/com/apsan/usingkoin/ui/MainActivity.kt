package com.apsan.usingkoin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.apsan.usingkoin.R

class MainActivity : AppCompatActivity() {
    val TAG = "tag"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().add(R.id.FrameLayout, HomeFragment()).commit()
    }
}