package com.elshader.ui.addtrader

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.elshader.R
import com.elshader.data.network.MyApi
import com.elshader.data.responses.TradersResponse
import com.elshader.utils.Coroutines
import com.elshader.utils.Resource
import com.elshader.utils.SharedData
import kotlinx.coroutines.launch
import java.io.IOException

class AddTraderViewModel (val app: Application) : AndroidViewModel(app) {

    var region_id: String? = null
    var name: String? = null
    var phone: String? = null
    var money_indebtedness: String? = null
    var boxes_indebtedness: String? = null

    var error = ObservableField<HashMap<String, String>>()
    val errorKeys = HashMap<String, String>()
    val addTraderData = MutableLiveData<Resource<*>>()
    val getRegionData = MutableLiveData<Resource<*>>()

    var data: SharedData? = SharedData(app)

    fun onClickAddTrader() {
        if (!Validate_TraderName() or !Validate_BoxesCount()|| !Validate_TraderBoxes() or !Validate_Money()) {
            return
        }
        addTraderData.value = Resource.Loading()
        viewModelScope.launch {
            addTrader()
        }



    }


    private suspend fun addTrader() {
        val currentUser: String? = data?.getValue(SharedData.ReturnValue.STRING, "token")

//        val response = MyApi().addGoods(seller_name!!, boxes_count!!, boxes_count!!,"Bearer "+currentUser!!)
//        if (response.isSuccessful
//                && null != response.body()
//        ) {
//            addTraderData.value = Resource.Success(response.body()!!)
//        } else {
//            val apiError: APIError = Gson().fromJson(response.errorBody()?.charStream(), APIError::class.java)
//            addTraderData.value = Resource.Error(IOException(response.message()))
//        }
    }


    fun setError(key: String, message: String) {
        errorKeys.put(key, message)
        error.set(errorKeys)
        error.notifyChange()
    }

    fun Validate_TraderName(): Boolean {
        if (name.isNullOrEmpty()) {
            setError("trader_name", app.getString(R.string.fill_input))
            return false
        }
        return true
    }


    fun Validate_BoxesCount(): Boolean {
        if (phone.isNullOrEmpty()) {
            setError("trader_phone", app.getString(R.string.fill_input))
            return false
        }
        return true
    }


    fun Validate_Money(): Boolean {
        if (phone.isNullOrEmpty()) {
            setError("trader_money", app.getString(R.string.fill_input))
            return false
        }
        return true
    }

    fun Validate_TraderBoxes(): Boolean {
        if (phone.isNullOrEmpty()) {
            setError("trader_boxes", app.getString(R.string.fill_input))
            return false
        }
        return true
    }

     suspend fun getRegions() {

         val currentUser: String? = data?.getValue(SharedData.ReturnValue.STRING, "token")

         Coroutines.main {
             getRegionData.value = Resource.Loading()

             val response = MyApi().getRegions("Bearer " + currentUser)
             if (response.isSuccessful
                     && null != response.body()
             ) {
                 getRegionData.value = Resource.Success(response.body()!!.data!!)
             } else {
                 getRegionData.value = Resource.Error(IOException(response.message()))
             }
         }
     }

}