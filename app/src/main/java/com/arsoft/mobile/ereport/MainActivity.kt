package com.arsoft.mobile.ereport

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.arsoft.mobile.ereport.module.register.RegisterActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startActivity(Intent(this@MainActivity, RegisterActivity::class.java))
    }
}
