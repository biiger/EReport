package com.arsoft.mobile.ereport.tutorial.tab

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arsoft.mobile.ereport.R
import kotlinx.android.synthetic.main.fragment_tutorial4.view.*

class Tutorial4 : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var contentView = inflater.inflate(R.layout.fragment_tutorial4,container,false)

        contentView.fragment_tutorial4_tv_title.text = "ได้รับรีพอร์ตผ่านทางอีเมล์ของคุณ"
        contentView.fragment_tutorial4_tv_content.text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In at ticidunt magna. Aliquam erat volutpat."

        return contentView
    }
}