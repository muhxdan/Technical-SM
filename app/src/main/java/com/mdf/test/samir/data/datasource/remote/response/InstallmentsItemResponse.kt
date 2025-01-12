package com.mdf.test.samir.data.datasource.remote.response

import com.google.gson.annotations.SerializedName

data class InstallmentsItemResponse(

    @field:SerializedName("amountDue")
    val amountDue: Int,

    @field:SerializedName("dueDate")
    val dueDate: String
)