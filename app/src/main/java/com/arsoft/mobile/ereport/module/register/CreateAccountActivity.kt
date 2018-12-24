package com.arsoft.mobile.ereport.module.register

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.arsoft.mobile.ereport.R
import kotlinx.android.synthetic.main.activity_create_account.*

class CreateAccountActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        activity_create_account__bt_next.setOnClickListener {
            val email = activity_create_account__et_email.text.toString()
            val pin = activity_create_account__et_pin.text.toString()
            val pinConfirm = activity_create_account__et_pin_confirm.text.toString()

            if (email.isEmpty()) {
                Toast.makeText(this, "กรุณากรอกอีเมล์", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if (pin.isEmpty()) {
                Toast.makeText(this, "กรุณากรอกรหัส PIN", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if (pinConfirm.isEmpty()) {
                Toast.makeText(this, "กรุณายืนยันรหัส PIN", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if (pin.length != 6 || pinConfirm.length != 6) {
                Toast.makeText(this, "กรุณากรอกรหัส PIN จำนวน 6 ตัว", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if (!pin.equals(pinConfirm)) {
                Toast.makeText(this, "รหัส PIN ไม่ตรงกัน", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            startActivity(Intent(this@CreateAccountActivity, CreateAccountDetailsActivity::class.java))
        }
    }
}
