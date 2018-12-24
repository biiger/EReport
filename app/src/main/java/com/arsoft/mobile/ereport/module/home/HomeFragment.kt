package com.arsoft.mobile.ereport.module.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arsoft.mobile.ereport.R
import com.arsoft.mobile.ereport.base.BaseFragment

class HomeFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var contentView = inflater.inflate(R.layout.fragment_home,container,false)
        return contentView
    }
}