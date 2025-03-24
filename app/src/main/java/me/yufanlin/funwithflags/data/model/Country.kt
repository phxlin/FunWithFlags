package me.yufanlin.funwithflags.data.model

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("name")
    val name: String?,
    @SerializedName("region")
    val region: String?,
    @SerializedName("capital")
    val capital: String?,
    @SerializedName("alpha2Code")
    val code: String?,
    @SerializedName("flagPNG")
    val flag: String?
)