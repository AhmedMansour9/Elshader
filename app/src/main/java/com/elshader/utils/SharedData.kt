package com.elshader.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

class SharedData {
    private var preferences: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null
    constructor(context: Context) {
        preferences = context.getSharedPreferences("shader", Context.MODE_PRIVATE)
        editor = preferences?.edit()
    }



    fun <V> getValue(returnValue: ReturnValue?, key: String?): V? {
        var vs: V? = null
        when (returnValue) {
            ReturnValue.INT -> vs = (preferences!!.getInt(key, 0)) as V
            ReturnValue.STRING -> vs = (preferences!!.getString(key, "")) as V
            ReturnValue.LONG -> vs = (preferences!!.getLong(key, 0L)) as V
            ReturnValue.BOOLEAN -> vs = (preferences!!.getBoolean(key, false)) as V
        }
        return vs
    }

    fun <V> putValue(key: String?, value: V) {
        if (value == Int::class.java) {
            val i = value as Int
            editor?.putInt(key, i)
            editor?.apply()
        } else if (value == String::class.java) {
            val i = value as String
            editor?.putString(key, i)
            editor?.apply()
        } else if (value == Float::class.java) {
            val x = value as Float
            editor?.putFloat(key, x)
            editor?.apply()
        } else if (value == Boolean::class.java) {
            val x = value as Boolean
            editor?.putBoolean(key, x)
            editor?.apply()
        } else if (value == Long::class.java) {
            val x = value as Long
            editor?.putLong(key, x)
            editor?.apply()
        }
    }

    fun clear() {
        editor!!.clear().apply()
    }


    enum class ReturnValue {
        INT, STRING, LONG, BOOLEAN
    }

}