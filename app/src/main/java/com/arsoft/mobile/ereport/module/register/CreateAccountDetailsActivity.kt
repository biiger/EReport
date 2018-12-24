package com.arsoft.mobile.ereport.module.register

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.Toast
import com.arsoft.mobile.ereport.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.fxn.pix.Pix
import kotlinx.android.synthetic.main.activity_create_account_details.*
import java.io.File
import com.arsoft.mobile.ereport.MainActivity
import android.content.pm.PackageManager
import com.arsoft.mobile.ereport.LoginActivity
import com.fxn.utility.PermUtil

class CreateAccountDetailsActivity : AppCompatActivity() {

    val arrProvince = arrayListOf(
        "เลือกจังหวัด",
        "กระบี่",
        "กรุงเทพมหานคร",
        "กาญจนบุรี",
        "กาฬสินธุ์",
        "กำแพงเพชร",
        "ขอนแก่น",
        "จันทบุรี",
        "ฉะเชิงเทรา",
        "ชลบุรี",
        "ชัยนาท",
        "ชัยภูมิ",
        "ชุมพร",
        "เชียงราย",
        "เชียงใหม่",
        "ตรัง",
        "ตราด",
        "ตาก",
        "นครนายก",
        "นครปฐม",
        "นครพนม",
        "นครราชสีมา",
        "นครศรีธรรมราช",
        "นครสวรรค์",
        "นนทบุรี",
        "นราธิวาส",
        "น่าน",
        "บุรีรัมย์",
        "ปทุมธานี",
        "ประจวบคีรีขันธ์",
        "ปราจีนบุรี",
        "ปัตตานี",
        "พะเยา",
        "พังงา",
        "พัทลุง",
        "พิจิตร",
        "พิษณุโลก",
        "เพชรบุรี",
        "เพชรบูรณ์",
        "แพร่",
        "ภูเก็ต",
        "มหาสารคาม",
        "มุกดาหาร",
        "แม่ฮ่องสอน",
        "ยโสธร",
        "ยะลา",
        "ร้อยเอ็ด",
        "ระนอง",
        "ระยอง",
        "ราชบุรี",
        "ลพบุรี",
        "ลำปาง",
        "ลำพูน",
        "เลย",
        "ศรีสะเกษ",
        "สกลนคร",
        "สงขลา",
        "สตูล",
        "สมุทรปราการ",
        "สมุทรสงคราม",
        "สมุทรสาคร",
        "สระแก้ว",
        "สระบุรี",
        "สิงห์บุรี",
        "สุโขทัย",
        "สุพรรณบุรี",
        "สุราษฎร์ธานี",
        "สุรินทร์",
        "หนองคาย",
        "หนองบัวลำภู",
        "อยุธยา",
        "อ่างทอง",
        "อำนาจเจริญ",
        "อุดรธานี",
        "อุตรดิตถ์",
        "อุทัยธานี",
        "อุบลราชธานี"
    )

    val arrAmphoe = arrayListOf("เลือกอำเภอ","เจริญนคร", "หนองแขม")
    val arrDistrict = arrayListOf("เลือกตำบล","คลองต้นไทร", "หนองค้างพลู")

    val ImageRequestCode: Int = 23145

    private val MY_PERMISSIONS_REQUEST_CAMERA = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account_details)

        //Adapter for spinner
        activity_create_account_details__spn_province.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, arrProvince)
        activity_create_account_details__spn_amphoe.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, arrAmphoe)
        activity_create_account_details__spn_district.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, arrDistrict)

        activity_create_account_details__rl_profile.setOnClickListener {
//            alertDialogSelectPhotoFrom()
            getCamera()
        }

        activity_create_account_details__bt_create_user.setOnClickListener {
            val name = activity_create_account_details__et_name.text
            val surname = activity_create_account_details__et_surname.text
            val tel = activity_create_account_details__et_tel.text
            val address = activity_create_account_details__et_address.text
//            val district = activity_create_account_details__et_district.text
//            val amphoe = activity_create_account_details__et_amphoe.text
            val district = activity_create_account_details__spn_district.selectedItem.toString()
            val amphoe = activity_create_account_details__spn_amphoe.selectedItem.toString()
            val province = activity_create_account_details__spn_province.selectedItem.toString()
            val postcode = activity_create_account_details__et_postcode.text

            if (name.isEmpty() || surname.isEmpty() || tel.isEmpty() || address.isEmpty() || district.equals("เลือกตำบล") || amphoe.equals("เลือกอำเภอ") || province.equals("เลือกจังหวัด") || postcode.isEmpty()) {
                Toast.makeText(this, "กรุณากรอกข้อมูลให้ครบ!", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            alertDialogConfirm()
        }
    }

    private fun alertDialogSelectPhotoFrom() {
        val builder = AlertDialog.Builder(this@CreateAccountDetailsActivity)
        builder.setTitle("คุณต้องการเลือกรูปจาก?")
        builder.setPositiveButton("Camera") { _, _ ->
            getCamera()
        }
        builder.setNegativeButton("Gallery") { _, _ ->
            getGallery()
        }

        // Finally, make the alert dialog using builder
        val dialog: AlertDialog = builder.create()

        // Display the alert dialog on app interface
        dialog.show()
    }

    private fun alertDialogConfirm() {
        val builder = AlertDialog.Builder(this@CreateAccountDetailsActivity)
        builder.setTitle("ยืนยันตัวตน")
        builder.setMessage("กรุณายืนยันตัวตนผ่านลิ้งค์ที่ระบบได้จัดส่งไปให้คุณผ่านทางอีเมล์ เพื่อทำการเปิดใช้งานบัญชีผู้ใช้")
        builder.setPositiveButton("ตกลง") { _, _ ->
            startActivity(Intent(this@CreateAccountDetailsActivity, LoginActivity::class.java))
        }
        // Finally, make the alert dialog using builder
        val dialog: AlertDialog = builder.create()

        // Display the alert dialog on app interface
        dialog.show()
    }

    private fun getGallery() {
        Pix.start(this,
            ImageRequestCode,
            1);
    }

    private fun getCamera() {
        Pix.start(this,
            ImageRequestCode,
            1);
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == ImageRequestCode) {
            val returnValue = data!!.getStringArrayListExtra(Pix.IMAGE_RESULTS)
            val file = File(returnValue[0])
            Glide.with(this)
                .load(file) // Uri of the picture
                .apply(RequestOptions.circleCropTransform().placeholder(R.drawable.user_photo))
                .into(activity_create_account_details__iv_profile)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            PermUtil.REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getCamera()
                } else {
                    Toast.makeText(this@CreateAccountDetailsActivity, "Approve permissions to open Pix ImagePicker", Toast.LENGTH_LONG).show()
                }
                return
            }
        }
    }
}
