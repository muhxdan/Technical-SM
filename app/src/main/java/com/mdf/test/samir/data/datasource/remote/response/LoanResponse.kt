package com.mdf.test.samir.data.datasource.remote.response

import com.google.gson.annotations.SerializedName

data class LoanResponse(

    @field:SerializedName("interestRate")
    val interestRate: Any,

    @field:SerializedName("amount")
    val amount: Int,

    @field:SerializedName("purpose")
    val purpose: String,

    @field:SerializedName("documents")
    val documents: List<DocumentsItemResponse>? = null,

    @field:SerializedName("borrower")
    val borrowerResponse: BorrowerResponse,

    @field:SerializedName("term")
    val term: Int,

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("collateral")
    val collateralResponse: CollateralResponse,

    @field:SerializedName("repaymentSchedule")
    val repaymentScheduleResponse: RepaymentScheduleResponse,

    @field:SerializedName("riskRating")
    val riskRating: String
)



