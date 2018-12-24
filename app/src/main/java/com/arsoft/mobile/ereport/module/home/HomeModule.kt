package com.arsoft.mobile.ereport.module.home

import com.arsoft.mobile.ereport.R
import com.arsoft.mobile.ereport.base.BaseArrayList
import com.arsoft.mobile.ereport.base.BaseMaster
import com.arsoft.mobile.ereport.base.BaseModule

class HomeModule : BaseModule(
    R.drawable.user,
    R.drawable.user,
    "หน้าแรก",
    0,
    HomeFragment()) {

    override fun setMaster(masterList: BaseArrayList<BaseMaster>) {

    }
}