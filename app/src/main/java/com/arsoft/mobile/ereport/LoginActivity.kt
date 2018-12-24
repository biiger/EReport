package com.arsoft.mobile.ereport

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.arsoft.mobile.ereport.module.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        activity_login__bt_login.setOnClickListener {
            startActivity(Intent(this@LoginActivity, PassCodeActivity::class.java))
        }

        activity_login__bt_register.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }
    }
}
