package com.elshader.ui.addgoods

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.elshader.R
import com.elshader.data.network.APIError
import com.elshader.data.network.MyApi
import com.elshader.utils.Resource
import com.elshader.utils.SharedData
import com.google.gson.Gson
import kotlinx.coroutines.launch
import java.io.IOException

class AddGoodsViewModel(val app: Application) : AndroidViewModel(app) {

    var seller_name: String? = null
    var boxes_count: String? = null
    var error = ObservableField<HashMap<String, String>>()
    val errorKeys = HashMap<String, String>()
    val authData = MutableLiveData<Resource<*>>()
     var data: SharedData? = SharedData(app)

    fun onClickAddGoods() {
        if (!Validate_SellerName() or !Validate_BoxesCount()) {
            return
        }
        authData.value = Resource.Loading()
        viewModelScope.launch {
            addGoods()
        }



    }


    private suspend fun addGoods() {
        val currentUser: String? = data?.getValue(SharedData.ReturnValue.STRING, "token")

        val response = MyApi().addGoods(seller_name!!, boxes_count!!, boxes_count!!,"Bearer "+currentUser!!)
        if (response.isSuccessful
                && null != response.body()
        ) {
            authData.value = Resource.Success(response.body()!!)
        } else {
            val apiError: APIError = Gson().fromJson(response.errorBody()?.charStream(), APIError::class.java)
            authData.value = Resource.Error(IOException(response.message()))
        }
    }


    fun setError(key: String, message: String) {
        errorKeys.put(key, message)
        error.set(errorKeys)
        error.notifyChange()
    }

    fun Validate_SellerName(): Boolean {
        if (seller_name.isNullOrEmpty()) {
            setError("seller_name", app.getString(R.string.fill_input))
            return false
        }
        return true
    }


    fun Validate_BoxesCount(): Boolean {
        if (boxes_count.isNullOrEmpty()) {
            setError("box_count", app.getString(R.string.fill_input))
            return false
        }
        return true
    }


}