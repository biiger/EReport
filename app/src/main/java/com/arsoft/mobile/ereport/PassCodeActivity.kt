package com.arsoft.mobile.ereport

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.arsoft.mobile.ereport.user.User
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_pass_code.*



class PassCodeActivity : AppCompatActivity(), View.OnClickListener {

    enum class Stage {
        ENTER, SET, CONFIRM
    }

    var stage = Stage.ENTER

    var passCode: String? = null

    var setPassCode: String = ""
    var confirmPassCode: String = ""

    var input: String = ""

    var inputCount = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pass_code)


        passCode = User.checkPassCode(this)
        if (passCode != null) {
            stage = Stage.ENTER
            pass_code_title.text = "กรุณายืนยันรหัส PIN"
        } else {
            stage = Stage.SET
            pass_code_title.text = "กรุณาใส่รหัส PIN"
        }
    }

    private fun input(num: Int) {
        if (input.length < 6) {
            if (num >= 0) {
                input += num.toString()
            } else if (input.isNotEmpty()) {
                input = input.substring(0, input.length - 1);
            }
            updateInput()

            if (input.length >= 6) doProcess()
        }
    }

    private fun doProcess() {
        when (stage) {
            Stage.ENTER -> {
                if (passCode.equals(input)) {
                    finish()
                } else {
                    inputCount++
                    Toasty.error(this, "incorrect : $inputCount times.", Toast.LENGTH_SHORT).show()
                    input = ""
                    updateInput()

                    if (inputCount >= 5) {
                        User.clearUser(this)
                        finish()
                    }
                }
            }
            Stage.SET -> {
                setPassCode = input
                input = ""
                stage = Stage.CONFIRM
                pass_code_title.text = "กรุณาใส่รหัสยืนยัน PIN"
                updateInput()
            }
            Stage.CONFIRM -> {
                if (setPassCode.equals(input)) {
                    User.setPassCode(this, input)
                    var intent = Intent(this@PassCodeActivity,MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toasty.error(this, "incorrect", Toast.LENGTH_SHORT).show()
                    input = ""
                    updateInput()
                }
            }
        }
    }

    private fun updateInput() {
        var count = input.length

        if (count >= 1)
            pass_code_input_1.setImageDrawable(getDrawable(R.drawable.ic_circle_solid))
        else
            pass_code_input_1.setImageDrawable(getDrawable(R.drawable.ic_circle_regular))
        if (count >= 2)
            pass_code_input_2.setImageDrawable(getDrawable(R.drawable.ic_circle_solid))
        else
            pass_code_input_2.setImageDrawable(getDrawable(R.drawable.ic_circle_regular))
        if (count >= 3)
            pass_code_input_3.setImageDrawable(getDrawable(R.drawable.ic_circle_solid))
        else
            pass_code_input_3.setImageDrawable(getDrawable(R.drawable.ic_circle_regular))
        if (count >= 4)
            pass_code_input_4.setImageDrawable(getDrawable(R.drawable.ic_circle_solid))
        else
            pass_code_input_4.setImageDrawable(getDrawable(R.drawable.ic_circle_regular))
        if (count >= 5)
            pass_code_input_5.setImageDrawable(getDrawable(R.drawable.ic_circle_solid))
        else
            pass_code_input_5.setImageDrawable(getDrawable(R.drawable.ic_circle_regular))
        if (count >= 6)
            pass_code_input_6.setImageDrawable(getDrawable(R.drawable.ic_circle_solid))
        else
            pass_code_input_6.setImageDrawable(getDrawable(R.drawable.ic_circle_regular))


    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.pass_code_delete -> {
                input(-1)
            }
            R.id.pass_code_0 -> {
                input(0)
            }
            R.id.pass_code_1 -> {
                input(1)
            }
            R.id.pass_code_2 -> {
                input(2)
            }
            R.id.pass_code_3 -> {
                input(3)
            }
            R.id.pass_code_4 -> {
                input(4)
            }
            R.id.pass_code_5 -> {
                input(5)
            }
            R.id.pass_code_6 -> {
                input(6)
            }
            R.id.pass_code_7 -> {
                input(7)
            }
            R.id.pass_code_8 -> {
                input(8)
            }
            R.id.pass_code_9 -> {
                input(9)
            }

        }
    }
}
