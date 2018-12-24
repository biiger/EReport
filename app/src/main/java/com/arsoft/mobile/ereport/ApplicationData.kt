package com.arsoft.mobile.ereport

import com.arsoft.mobile.ereport.base.BaseModule

object ApplicationData {
    val otherModuleList: ArrayList<BaseModule> = ArrayList()
    val moduleList: ArrayList<BaseModule> = ArrayList()

    fun addModule(module: BaseModule) {
        this.moduleList.add(module)
    }

    fun addOtherModule(module: BaseModule) {
        this.otherModuleList.add(module)
    }

    fun getModule(name: String) = moduleList.find { it ->
        it.moduleTitle == name
    }

    fun getOtherModule(name: String) = otherModuleList.find { it ->
        it.moduleTitle == name
    }
}