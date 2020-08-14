package com.elshader.utils

import android.content.Context
import com.kaopiz.kprogresshud.KProgressHUD

class Loading {

    companion object{
        private lateinit var hud: KProgressHUD
       fun Show(context:Context){
           hud =  KProgressHUD.create(context)
               .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
               .setCancellable(false)
               .setAnimationSpeed(2)
               .setDimAmount(0.5f)
               .show();
       }
       fun Disable(){
           hud.dismiss()
       }

        fun Showing():Boolean{
            if(hud.isShowing){
              return true
            }else {
                return false
            }
        }
    }



}