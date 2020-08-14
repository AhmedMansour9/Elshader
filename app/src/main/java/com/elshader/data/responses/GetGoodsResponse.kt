package com.elshader.data.responses


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class GetGoodsResponse(
    @SerializedName("data")
    var `data`: List<Data?>?,
    @SerializedName("links")
    var links: Links?,
    @SerializedName("meta")
    var meta: Meta?
) : Parcelable {
    @SuppressLint("ParcelCreator")
    @Parcelize
    data class Data(
        @SerializedName("created_at")
        var createdAt: String?,
        @SerializedName("id")
        var id: Int?,
        @SerializedName("remaining_boxes")
        var remainingBoxes: String?,
        @SerializedName("seller_name")
        var sellerName: String?,
        @SerializedName("total_boxes")
        var totalBoxes: String?,
        @SerializedName("updated_at")
        var updatedAt: String?
    ) : Parcelable

    @SuppressLint("ParcelCreator")
    @Parcelize
    data class Links(
        @SerializedName("first")
        var first: String?,
        @SerializedName("last")
        var last: String?
    ) : Parcelable

    @SuppressLint("ParcelCreator")
    @Parcelize
    data class Meta(
        @SerializedName("current_page")
        var currentPage: Int?,
        @SerializedName("from")
        var from: Int?,
        @SerializedName("last_page")
        var lastPage: Int?,
        @SerializedName("path")
        var path: String?,
        @SerializedName("per_page")
        var perPage: Int?,
        @SerializedName("to")
        var to: Int?,
        @SerializedName("total")
        var total: Int?
    ) : Parcelable
}