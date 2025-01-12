package com.mdf.test.samir.data.datasource.remote.response

import com.google.gson.annotations.SerializedName

data class RepaymentScheduleResponse(

    @field:SerializedName("installments")
    val installments: List<InstallmentsItemResponse>
)