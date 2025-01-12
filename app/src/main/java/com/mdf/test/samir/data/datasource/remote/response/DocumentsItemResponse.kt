package com.mdf.test.samir.data.datasource.remote.response

import com.google.gson.annotations.SerializedName

data class DocumentsItemResponse(

    @field:SerializedName("type")
    val type: String,

    @field:SerializedName("url")
    val url: String
)