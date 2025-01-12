package com.mdf.test.samir.data.datasource.remote.response

import com.google.gson.annotations.SerializedName

data class CollateralResponse(

    @field:SerializedName("type")
    val type: String,

    @field:SerializedName("value")
    val value: Int
)