package com.mdf.test.samir.data.datasource.remote.response

import com.google.gson.annotations.SerializedName

data class BorrowerResponse(

    @field:SerializedName("creditScore")
    val creditScore: Int,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("email")
    val email: String
)
