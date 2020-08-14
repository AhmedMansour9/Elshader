package com.elshader.ui.home

import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.elshader.CustomBottomNavigationView1
import com.elshader.R
import kotlinx.android.synthetic.main.activity_bottom_navigate.*


class BottomNavigate : AppCompatActivity() {

    lateinit var navController: NavController
    var toolbar: Toolbar? = null
    private lateinit var DataSaver: SharedPreferences
    var UserToken: String? = String()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigate)
        setupViews()


    }


    fun setupViews() {
        var navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupWithNavController(customBottomBar, navHostFragment.navController)
        var appBarConfiguration = AppBarConfiguration(setOf(R.id.nav_Traders, R.id.nav_Filter,
                R.id.nav_Addgood,
                R.id.nav_AddTrader))

        setupWithNavController(customBottomBar, navHostFragment.navController)


    }


    override fun onBackPressed() {
        if (navController.graph.startDestination == navController.currentDestination?.id) {
            Exit()

        } else {
            super.onBackPressed()
        }
    }


    fun Exit() {
        var dialog = AlertDialog.Builder(this)
                .setMessage(resources.getString(R.string.exit_message))
                .setPositiveButton(resources.getString(R.string.yes), DialogInterface.OnClickListener { dialog, which ->
                    finish()
                })
                .setNegativeButton(resources.getString(R.string.no), DialogInterface.OnClickListener { dialog, which ->
                    return@OnClickListener
                })
                .show()
        val buttonPositive: Button = dialog.getButton(DialogInterface.BUTTON_POSITIVE)
        buttonPositive.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary))
        val buttonNegative: Button = dialog.getButton(DialogInterface.BUTTON_NEGATIVE)
        buttonNegative.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary))

    }
}