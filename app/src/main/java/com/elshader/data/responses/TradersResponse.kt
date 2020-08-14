package com.elshader.data.responses


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class TradersResponse(
    @SerializedName("data")
    var `data`: Data?
) : Parcelable {
    @SuppressLint("ParcelCreator")
    @Parcelize
    data class Data(
        @SerializedName("boxes_indebtedness")
        var boxesIndebtedness: String?,
        @SerializedName("created_at")
        var createdAt: String?,
        @SerializedName("id")
        var id: Int?,
        @SerializedName("money_indebtedness")
        var moneyIndebtedness: String?,
        @SerializedName("name")
        var name: String?,
        @SerializedName("phone")
        var phone: String?,
        @SerializedName("region_id")
        var regionId: String?,
        @SerializedName("updated_at")
        var updatedAt: String?
    ) : Parcelable
}