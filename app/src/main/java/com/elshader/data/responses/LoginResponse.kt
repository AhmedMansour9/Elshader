package com.elshader.data.responses


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class LoginResponse(
    @SerializedName("data")
    var `data`: Data?
) : Parcelable {
    @SuppressLint("ParcelCreator")
    @Parcelize
    data class Data(
        @SerializedName("auth_token")
        var authToken: AuthToken?,
        @SerializedName("user")
        var user: User?
    ) : Parcelable {
        @SuppressLint("ParcelCreator")
        @Parcelize
        data class AuthToken(
            @SerializedName("access_token")
            var accessToken: String?,
            @SerializedName("expires_at")
            var expiresAt: String?,
            @SerializedName("token_type")
            var tokenType: String?
        ) : Parcelable

        @SuppressLint("ParcelCreator")
        @Parcelize
        data class User(
            @SerializedName("email")
            var email: String?,
            @SerializedName("email_verified_at")
            var emailVerifiedAt: String?,
            @SerializedName("id")
            var id: Int?,
            @SerializedName("name")
            var name: String?
        ) : Parcelable
    }
}