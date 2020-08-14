package com.elshader.ui.splash

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.elshader.R
import com.elshader.ui.auth.LoginActivity
import com.elshader.ui.home.BottomNavigate
import com.elshader.utils.LocalizationUtil.applyLanguage
import com.elshader.utils.SharedData
import com.elshader.utils.toast
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit

class SplashActivity : AppCompatActivity() {
    private var data: SharedData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FullScreen()
        applyLanguage(this)
        setContentView(R.layout.activity_splash)
        init()
        CoroutineScope(Dispatchers.IO).launch {
            delay(TimeUnit.SECONDS.toMillis(3))
            withContext(Dispatchers.Main) {
                val currentUser: String? = data?.getValue(SharedData.ReturnValue.STRING, "token")
                if(currentUser.isNullOrEmpty()) {
                    val intent = Intent(this@SplashActivity, LoginActivity::class.java)
                    startActivity(intent)
                }else {
                    val intent = Intent(this@SplashActivity, BottomNavigate::class.java)
                    startActivity(intent)
                }
                finish()

            }
        }
    }

    fun init(){
        data = SharedData(this)
    }


    private fun FullScreen(){
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
    }
}