package com.elshader.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.elshader.R
import com.elshader.data.responses.LoginResponse
import com.elshader.databinding.ActivityLoginBinding
import com.elshader.ui.home.BottomNavigate
import com.elshader.utils.*

class LoginActivity : AppCompatActivity() {
    private var data: SharedData? = null
    private val loginViewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.viewmodel = loginViewModel
        setupData()

    }

    fun setupData() {
        data = SharedData(this)
        subscribeAuthData()
    }


    private fun subscribeAuthData() {
        loginViewModel.authData.removeObservers(this)
        loginViewModel.authData.observe(this, Observer { resources ->
            run {
                when (resources) {
                    is Resource.Success -> {
                        success(resources.data as LoginResponse.Data?)
                    }

                    is Resource.Error -> {
                        error(resources.exception.message!!)
                    }

                    is Resource.Loading -> {
                        loading()
                    }
                }

            }
        })

    }


    private fun success(userdata: LoginResponse.Data?) {
        Loading.Disable()
        data?.putValue("token", userdata?.authToken?.accessToken)
        val intent = Intent(this, BottomNavigate::class.java)
        startActivity(intent)
        finish()
    }

    private fun error(message: String) {
        Loading.Disable()
        toast(message)
    }

    private fun loading() {
       Loading.Show(this)
    }

}

























//private fun subscribeAuthData() {
//    loginViewModel.authData.removeObservers(this)
//    loginViewModel.authData.observe(this, Observer { resources ->
//        run {
//            when (resources) {
//                is Resource.Success -> {
//                    success(resources.data as LoginResponse.Data?)
//                }
//
//                is Resource.Error -> {
//                    error(resources.exception.message!!)
//                }
//
//                is Resource.Loading -> {
//                    loading()
//                }
//            }
//
//        }
//    })
//
//}
