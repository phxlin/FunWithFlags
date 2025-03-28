package me.yufanlin.funwithflags.data.model

import com.google.gson.annotations.SerializedName

data class Country(
    val name: String?,
    val region: String?,
    val capital: String?,
    @SerializedName("alpha2Code")
    val code: String?,
    @SerializedName("flagPNG")
    val flag: String?
)