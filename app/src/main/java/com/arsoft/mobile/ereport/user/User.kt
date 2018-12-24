package com.arsoft.mobile.ereport.user

import android.content.Context
import com.arsoft.mobile.ereport.utils.clearCache
import com.arsoft.mobile.ereport.utils.getCache
import com.arsoft.mobile.ereport.utils.setCache

object User {
    private val CACHE_USER: String = "app_cache_user"
    private val CACHE_USER_PASS_CODE: String = "app_cache_user_pass_code"
    private val CACHE_USER_TIME: Long = 30 * 86400

    fun checkPassCode(context: Context) = CACHE_USER_PASS_CODE.getCache(context)

    fun setPassCode(context: Context, passCode: String) = CACHE_USER_PASS_CODE.setCache(context, passCode, CACHE_USER_TIME)

    fun clearUser(context: Context) {
        CACHE_USER.clearCache(context)
        CACHE_USER_PASS_CODE.clearCache(context)
    }

}