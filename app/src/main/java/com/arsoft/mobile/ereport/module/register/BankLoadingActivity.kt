package com.arsoft.mobile.ereport.module.register

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.arsoft.mobile.ereport.R
import kotlinx.android.synthetic.main.activity_bank_loading.*

class BankLoadingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bank_loading)

        val bankSelected = intent.getStringExtra("bankSelected")

        when (bankSelected) {
            "ธนาคารกรุงเทพ" -> activity_bank_loading__iv_logo.setImageResource(R.drawable.bangkok_bank_logo)
            "ธนาคารกสิกรไทย" -> activity_bank_loading__iv_logo.setImageResource(R.drawable.kbank)
            "ธนาคารไทยพาณิชย์" -> activity_bank_loading__iv_logo.setImageResource(R.drawable.scb_logo)
            "ธนาคารกรุงศรีอยุธยา" -> activity_bank_loading__iv_logo.setImageResource(R.drawable.krungsri_logo)
        }

        Handler().postDelayed({
            startActivity(Intent(this@BankLoadingActivity, CreateAccountActivity::class.java))
        }, 2000)
    }

    override fun onStart() {
        super.onStart()
        Handler().postDelayed({
            startActivity(Intent(this@BankLoadingActivity, CreateAccountActivity::class.java))
        }, 2000)
    }
}
