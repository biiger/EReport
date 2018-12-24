package com.arsoft.mobile.ereport.base

import com.arsoft.mobile.ereport.intf.ICallback

data class BaseMenuBottom(val moduleIcon: Int, val moduleIconActive: Int, val callback: ICallback)

abstract class BaseModule(val moduleIcon: Int, val moduleIconActive: Int, val moduleTitle: String, val moduleTitleImg: Int, val fragment: BaseFragment) {
    var active: Boolean = false

    var menuBottom: BaseMenuBottom? = null

    val masterList: BaseArrayList<BaseMaster> = BaseArrayList()

    var openingData: Any? = null

    abstract fun setMaster(masterList: BaseArrayList<BaseMaster>)

    init {
        this.setMaster(masterList)
    }

    fun shouldUpdateMaster(): Boolean {
        var should = false
        masterList.forEach {
            if (it.data is BaseArrayList<*>) {
                if ((it.data as BaseArrayList<*>).isEmpty())
                    should = true
            } else if (it.data is BaseModel) {
                if ((it.data as BaseModel).updateDate == 0L)
                    should = true
            }
        }
        return should
    }

    fun updateMaster(callback: ICallback) {
        var countDown = masterList.size
        var failedCode = 0
        var hasFailed = false
        var failedFor = ""
        val apiCallback = object : ICallback {
            override fun changed(tag: Any, code: Int, data: Any) {
                masterList.forEach {
                    if (it.target.equals(tag.toString())) {
                        if (code == 200) {
                            it.setValue(data.toString())
                        } else {
                            hasFailed = true
                            failedCode = code
                            failedFor += "[" + it.module + ":" + it.target + "->" + data.toString() + "]" + System.lineSeparator()
                        }
                        countDown--
                        if (countDown <= 0) {
                            if (hasFailed)
                                callback.changed("master", failedCode, failedFor)
                            else
                                callback.changed("master", 200, "done")
                        }
                    }
                }
            }
        }
//        masterList.forEach {
//            WebAPI.request(it.url, it.module, it.target, null, it.target, apiCallback)
//        }
    }

    fun getMasterByTarget(target: String): BaseMaster? = masterList.find { it ->
        it.target == target
    }
}