package com.arsoft.mobile.ereport

import android.content.pm.ActivityInfo
import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import com.appimake.drawerapp.*
import com.arsoft.mobile.ereport.module.home.HomeModule
import kotlinx.android.synthetic.main.nav_menu_header.view.*

class MainActivity : DrawerAppActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        super.selectedPosition = 0
        super.defaultContentBackground = resources.getColor(R.color.colorWhite,null)
        super.navBG = resources.getColor(R.color.colorWhite,null)
        super.menuItemSelectedBackgound = resources.getColor(R.color.transparent_white_80,null)

        super.onCreate(savedInstanceState)

        val hambergerIcon = findViewById<ImageView>(R.id.navigation_bar_menu)
        hambergerIcon.setPadding(40, 40, 40, 40)
        hambergerIcon.setImageResource(R.drawable.ic_hamberger_menu)

    }

    override fun setDAHeader(): Any {
        val layout = LinearLayout(this)
        layout.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT)

        val view = LayoutInflater.from(this@MainActivity).inflate(R.layout.nav_menu_header, layout, false)
//        view.nav_menu_header_tv_firstname.text = User.getUser()!!.user.firstName
//        view.nav_menu_header_tv_lastname.text = User.getUser()!!.user.lastName
        view.nav_menu_header_ll_menu_header.setOnClickListener {
//            var intent = Intent(this@MainActivity,UserProfileActivity::class.java)
//            startActivity(intent)
        }

        return view
    }

    override fun setDASelectedCallBack() = object : DACallBack {
        override fun onClick(data: Any) {

        }
    }

    override fun setMenuItemList(ItemList: ArrayList<DAMenuItem>) {
        ApplicationData.moduleList.clear()
        ApplicationData.addModule(HomeModule())

        val layout = LinearLayout(this)
        layout.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT)

        ApplicationData.moduleList.forEach {

            if (it.moduleTitle.equals("Setting")) {
                val line = LinearLayout(this)
                val pram = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT)
                pram.setMargins(32, 16, 32, 16)
                line.layoutParams = pram
                line.layoutParams.height = 5
                line.setBackgroundColor(Color.WHITE)
                ItemList.add(DAMenuItem("line", line, null, null, null, null))
            }

            ItemList.add(DAMenuItem(if (it.moduleTitleImg != 0) it.moduleTitleImg else it.moduleTitle,
                DAMenuItemDefault(it.moduleTitle,
                    DATextStyle("#000000", "#000000", Typeface.DEFAULT, 18f),
                    it.moduleIcon,
                    it.moduleIconActive,
                    null),
                null,
                DANavBarStyle(if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) resources.getColor(R.color.colorPrimary, null) else resources.getColor(R.color.colorPrimary,null),
                    Color.WHITE,
                    24f,
                    if (it.moduleTitle.equals("Home") || it.moduleTitle.equals("Order List"))
                        DAActionItem(
                            0,
                            null,
                            object : DACallBack {
                                override fun onClick(data: Any) {
//                                    val i = Intent(this@MainActivity, NotificationActivity::class.java)
//                                    startActivity(i)
                                }
                            }
                        )
                    else null,
                    null),
                R.color.colorWhite,
                it.fragment))
        }
    }

}
