package com.elshader.data.network

import com.elshader.data.responses.AddGoodsResponse
import com.elshader.data.responses.LoginResponse
import com.elshader.data.responses.RegionResponses
import com.elshader.data.responses.TradersResponse
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

interface MyApi {

    @FormUrlEncoded
    @POST("auth/login")
   suspend fun userLogin(
       @Field("email") email:String,
       @Field("password") password:String
    ) : Response<LoginResponse>


    @FormUrlEncoded
    @POST("purchasing-deals")
    suspend fun addGoods(
            @Field("seller_name") Name:String,
            @Field("total_boxes") Total:String,
            @Field("remaining_boxes") TotalRemain:String,
            @Header("Authorization")auth:String
    ) : Response<AddGoodsResponse>



    @FormUrlEncoded
    @GET("traders")
    suspend fun getTraders(
            @Header("Authorization")auth:String
    ) : Response<TradersResponse>

    @GET("regions")
    suspend fun getRegions(
            @Header("Authorization")auth:String
    ) : Response<RegionResponses>

    companion object {
        operator  fun invoke(): MyApi{
            val interceptor= HttpLoggingInterceptor();
            interceptor.level=HttpLoggingInterceptor.Level.BODY
            val inter=MyInterceptor()

            val okHttpClient = OkHttpClient().newBuilder()
                    .connectTimeout(10, TimeUnit.MINUTES) // connect timeout
                    .readTimeout(10, TimeUnit.MINUTES)
                    .connectTimeout(10, TimeUnit.MINUTES)
                    .addInterceptor(interceptor)
                    .addInterceptor(inter)
                    .build()

            return Retrofit.Builder()
                    .baseUrl("http://stock.taxialsultan.com/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()
                    .create(MyApi::class.java)

        }
    }


}