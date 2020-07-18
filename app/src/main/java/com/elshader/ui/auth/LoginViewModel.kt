package com.elshader.ui.auth

import android.util.Patterns
import android.view.View
import androidx.lifecycle.ViewModel
import java.util.regex.Pattern

class LoginViewModel : ViewModel() {
     val PASSWORD_PATTERN = Pattern.compile(
            "^" + "(?=\\S+$)" + ".{5,}"
    )
     var email:String?=null
     var password:String?=null
     var loginlistner:LoginListner?=null

    fun onClickLogin(view:View){
        loginlistner?.onStartt()
       if(!Validate_Email() or !Validate_Password()){
           loginlistner?.onError("error")
           return
       }
        loginlistner?.onSuccess()

    }

  fun Validate_Email():Boolean{
    if(email.isNullOrEmpty()){

        return false
    }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) run {

        return false
    }
      return true
    }
    fun Validate_Password():Boolean{
        if(password.isNullOrEmpty()){

            return false
        }else if (!PASSWORD_PATTERN.matcher(password).matches()) run {

            return false
        }
        return true
    }
}