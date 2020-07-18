package com.elshader.utils

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import java.util.*

object LocalizationUtil {
    private var sharedData: SharedData? = null

    @SuppressWarnings("Deprecated in Android 17")
    fun applyLanguage(context: Context): Context {
        sharedData = SharedData(context)
//        val language: String? = sharedData!!.getValue(SharedData.ReturnValue.STRING, "lang")
        val language:String by lazy { "ar" }
        val locale = Locale(language)
        val configuration = context.resources.configuration
        val displayMetrics = context.resources.displayMetrics

        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        context.resources.updateConfiguration(
                config,
                context.resources.displayMetrics)
           return context
    }




}