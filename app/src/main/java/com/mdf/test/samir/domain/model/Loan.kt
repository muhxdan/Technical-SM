package com.mdf.test.samir.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Loan(
    val id: String,
    val amount: Int,
    val term: Int,
    val purpose: String,
    val interestRate: Double?,
    val riskRating: String,
    val borrower: Borrower,
    val collateral: Collateral,
    val repaymentSchedule: RepaymentSchedule,
    val documents: List<Document>?,
    val createdAt: Long,
) : Parcelable
