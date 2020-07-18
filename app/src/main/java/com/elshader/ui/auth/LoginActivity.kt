package com.elshader.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.elshader.R
import com.elshader.databinding.ActivityLoginBinding
import com.elshader.utils.LocalizationUtil.applyLanguage
import com.elshader.utils.toast

class LoginActivity : AppCompatActivity(),LoginListner {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        applyLanguage(this)
        val binding:ActivityLoginBinding=DataBindingUtil.setContentView(this,R.layout.activity_login)
        val loginViewModel = ViewModelProvider.NewInstanceFactory().create(LoginViewModel::class.java)
        binding.viewmodel=loginViewModel
        loginViewModel.loginlistner=this


    }

    override fun onSuccess() {
        toast("success")
    }

    override fun onError(message: String) {
        toast("error")
    }
    override fun onStartt() {
        toast("start")

    }
}