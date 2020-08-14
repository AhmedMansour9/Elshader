package com.elshader.data.responses


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class AddGoodsResponse(
    @SerializedName("data")
    var `data`: Data?
) : Parcelable {
    @SuppressLint("ParcelCreator")
    @Parcelize
    data class Data(
        @SerializedName("bets")
        var bets: String?,
        @SerializedName("boxe_price")
        var boxePrice: String?,
        @SerializedName("boxes_count")
        var boxesCount: String?,
        @SerializedName("created_at")
        var createdAt: String?,
        @SerializedName("id")
        var id: Int?,
        @SerializedName("total_boxes_price")
        var totalBoxesPrice: String?,
        @SerializedName("total_paid")
        var totalPaid: String?,
        @SerializedName("total_unpaid")
        var totalUnpaid: String?,
        @SerializedName("trader")
        var trader: Trader?,
        @SerializedName("trader_id")
        var traderId: String?,
        @SerializedName("updated_at")
        var updatedAt: String?
    ) : Parcelable {
        @SuppressLint("ParcelCreator")
        @Parcelize
        data class Trader(
            @SerializedName("boxes_indebtedness")
            var boxesIndebtedness: Int?,
            @SerializedName("created_at")
            var createdAt: String?,
            @SerializedName("id")
            var id: Int?,
            @SerializedName("money_indebtedness")
            var moneyIndebtedness: Int?,
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
}