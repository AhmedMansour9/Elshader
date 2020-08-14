package com.elshader.ui.auth

import android.app.Application
import android.util.Log
import android.util.Patterns
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.elshader.R
import com.elshader.data.network.APIError
import com.elshader.data.network.MyApi
import com.elshader.utils.Coroutines
import com.elshader.utils.Resource
 import com.google.gson.Gson
import java.io.IOException

class LoginViewModel(val app: Application) : AndroidViewModel(app) {


    var email: String? = null
    var password: String? = null
    var error = ObservableField<HashMap<String, String>>()
    val errorKeys = HashMap<String, String>()
    val authData = MutableLiveData<Resource<*>>()

    fun onClickLogin() {
        if (!Validate_Email() or !Validate_Password()) {
            return
        }
        authData.value = Resource.Loading()

        Coroutines.main {
            val response = MyApi().userLogin(email!!, password!!)
            if (response.isSuccessful) {
                authData.value = Resource.Success(response.body()!!.data!!)
            } else {
                val apiError: APIError = Gson().fromJson(response.errorBody()?.charStream(), APIError::class.java)
                authData.value = Resource.Error(IOException(apiError.errors.email.get(0)))
            }

        }


    }


    fun setError(key: String, message: String) {
        errorKeys.put(key, message)
        error.set(errorKeys)
        error.notifyChange()
    }

    fun Validate_Email(): Boolean {
        if (email.isNullOrEmpty()) {
            setError("email", app.getString(R.string.fill_input))
            return false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) run {
            setError("email", app.getString(R.string.validate_Email))
            return false

        }
        return true
    }


    fun Validate_Password(): Boolean {
        if (password.isNullOrEmpty()) {
            setError("password", app.getString(R.string.fill_input))

            return false
        } else if (password.toString().length < 6) run {
            setError("password", app.getString(R.string.pasweak))

            return false
        }
        return true
    }


}























//            val response = UserRespositry().userLogin(email!!, password!!)
//            if (response.isSuccessful) {
//                authData.value = Resource.Success(response.body()?.data)
//            } else {
//                val apiError: APIError = Gson().fromJson(response.errorBody()?.charStream(), APIError::class.java)
//                authData.value = Resource.Error(apiError.errors.email.get(0), null)
//            }