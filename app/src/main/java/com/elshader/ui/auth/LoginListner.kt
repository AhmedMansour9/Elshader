package com.elshader.ui.auth

interface LoginListner {

    fun onStartt()
    fun onSuccess()
    fun onError(message:String)
}