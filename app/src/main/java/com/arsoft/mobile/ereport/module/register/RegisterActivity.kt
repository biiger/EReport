package com.arsoft.mobile.ereport.module.register

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.arsoft.mobile.ereport.R
import com.arsoft.mobile.ereport.module.register.model.SpinnerDataModel
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    val arrBank = arrayOf("เลือกธนาคาร", "ธนาคารกรุงเทพ", "ธนาคารกสิกรไทย", "ธนาคารไทยพาณิชย์", "ธนาคารกรุงศรีอยุธยา")
    var arrData = arrayListOf<SpinnerDataModel>(
        SpinnerDataModel("เลือกธนาคาร", 0),
        SpinnerDataModel("ธนาคารกรุงเทพ", R.drawable.ic_bangkok_bank),
        SpinnerDataModel("ธนาคารกสิกรไทย", R.drawable.ic_kbank),
        SpinnerDataModel("ธนาคารไทยพาณิชย์", R.drawable.ic_scb),
        SpinnerDataModel("ธนาคารกรุงศรีอยุธยา", R.drawable.ic_krungsri)
    )
    var bankSelected: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        //Adapter for spinner
//        activity_register__spn_bank.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, arrBank)

        activity_register__spn_bank.adapter = SpinnerAdapter(this, arrData)

        activity_register__spn_bank.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (parent != null && parent.selectedView != null) {
                    arrData.forEach {
                        bankSelected = arrData[position].name
                    }
                }
            }
        }

        activity_register__bt_assure.setOnClickListener {
            val id_card = activity_register__et_id_card.text.toString()
            val bankSelected = activity_register__spn_bank.selectedItem.toString()

            if (id_card.isEmpty()) {
                Toast.makeText(this@RegisterActivity, "กรุณากรอกเลขบัตรประชาชน", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (id_card.length < 13) {
                Toast.makeText(this@RegisterActivity, "กรุณากรอกเลขบัตรประชาชนให้ถูกต้อง", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (bankSelected.equals("เลือกธนาคาร")) {
                Toast.makeText(this@RegisterActivity, "กรุณากรอกเลือกธนาคาร", Toast.LENGTH_LONG).show()
                return@setOnClickListener

            }

            discliamAlert()
        }
    }

    fun discliamAlert() {
        val builder = AlertDialog.Builder(this@RegisterActivity)
        builder.setTitle("ข้อตกลงและเงื่อนไข")
        builder.setMessage(
            "Nemu emm \\psam volupmem qma\n" +
                    "vu‘uptas m aspematur am can am\n" +
                    "lugn, sed qwa (unsequunlur magm\n" +
                    "do‘mes e05 qul vahone vo‘uptakem\n" +
                    "seqw\n" +
                    "\n" +
                    "1 nesmunt Neque porro qmsquam est,\n" +
                    "qul do‘orem Apsum qma do‘or sit amet,\n" +
                    "consectetuv, adlplscw veht, sed qma mm\n" +
                    "numquam ems mudw tempera modum\n" +
                    "m \\abore et dolore magnam ahquam\n" +
                    "queerat vumpmem Ut emm ad mmme\n" +
                    "venlam, qws nosuum exercllahonem\n" +
                    "ullam\n" +
                    "\n" +
                    "2 cuvpons susupn labonosam, nlsw m\n" +
                    "ahqmd ex ea (ommodl consequamn\n" +
                    "Qws amem ve‘ sum 'ure reprehendem\n" +
                    "qm m ea vompme velll esse quam mm\n" +
                    "mo‘esuae cansequatur, ve‘ \\Hum qul\n" +
                    "do‘mem eum lugwat qua volumes nuns"
        )

        builder.setPositiveButton("ยอมรับ") { dialog, which ->
            startActivity(
                Intent(this@RegisterActivity, BankLoadingActivity::class.java).putExtra(
                    "bankSelected",
                    bankSelected
                )
            )
        }
        builder.setNegativeButton("ยกเลิก") { dialog, which ->

        }

        // Finally, make the alert dialog using builder
        val dialog: AlertDialog = builder.create()

        // Display the alert dialog on app interface
        dialog.show()

        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#213f9a"))
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.DKGRAY)
    }

    class SpinnerAdapter(val context: Context, val arrList: ArrayList<SpinnerDataModel>) : BaseAdapter() {
        private val mInflater: LayoutInflater = LayoutInflater.from(context)

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

            val view: View
            val viewHolder: ItemRowHolder
            if (convertView == null) {
                view = mInflater.inflate(R.layout.spinner_list_item, parent, false)
                viewHolder = ItemRowHolder(view)
                view.tag = viewHolder
            } else {
                view = convertView
                viewHolder = view.tag as ItemRowHolder
            }

            val params = view.layoutParams

            if (getItem(position).name.equals("เลือกธนาคาร"))
                viewHolder.ivIcon.visibility = View.GONE

            viewHolder.ivIcon.setImageResource(getItem(position).icon)
            viewHolder.tvName.text = getItem(position).name

            view.layoutParams = params

            return view
        }

        override fun getItem(position: Int): SpinnerDataModel {
            return arrList[position]
        }

        override fun getItemId(position: Int): Long {
//            return getItem(position).uniqueID
            return 0
        }

        override fun getCount(): Int {
            return arrList.size
        }

        private class ItemRowHolder(row: View?) {

            val ivIcon: ImageView
            val tvName: TextView

            init {
                this.ivIcon = row!!.findViewById(R.id.spinner_list_item_iv_icon) as ImageView
                this.tvName = row!!.findViewById(R.id.spinner_list_item_tv_name) as TextView
            }
        }
    }
}
