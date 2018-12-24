package com.arsoft.mobile.ereport.intf

interface ICallback {
    fun changed(tag: Any, code: Int, data: Any)
}