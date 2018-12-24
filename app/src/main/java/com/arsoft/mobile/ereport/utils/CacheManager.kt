package com.arsoft.mobile.ereport.utils

import android.content.Context
import android.content.SharedPreferences
import java.util.*

object CacheManager{
    val BEGINDATE = "BeginDate"
    val EXPIREDATE = "ExpireDate"

    fun setDate(sp: SharedPreferences, timeInterval: Long) {
        val now = Calendar.getInstance().timeInMillis
        val editor: SharedPreferences.Editor = sp.edit()
        editor.putLong(BEGINDATE, now)
        editor.putLong(EXPIREDATE, now + (timeInterval * 1000))
        editor.apply()
    }

    fun getExpireDate(sp: SharedPreferences): Long {
        val now = Calendar.getInstance().timeInMillis
        return (sp.getLong(EXPIREDATE, 0) - now)
    }
}

fun String.setCache(context: Context, obj: String, timeInterval: Long) {
    val sp = context.getSharedPreferences(this, Context.MODE_PRIVATE)
    sp.edit().putString(this, obj).apply()
    CacheManager.setDate(sp, timeInterval)
}

fun String.getCache(context: Context): String? {
    val sp = context.getSharedPreferences(this, Context.MODE_PRIVATE)

    if (CacheManager.getExpireDate(sp) < 0)
        this.clearCache(context)

    return sp.getString(this, null)
}

fun String.clearCache(context: Context) {
    context.getSharedPreferences(this, Context.MODE_PRIVATE).edit().clear().apply()
}