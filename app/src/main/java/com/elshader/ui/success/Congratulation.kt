package com.elshader.ui.success

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.databinding.DataBindingUtil
import com.elshader.R
import com.elshader.databinding.ActivityLoginBinding

class Congratulation : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_congratulation)


    }
}