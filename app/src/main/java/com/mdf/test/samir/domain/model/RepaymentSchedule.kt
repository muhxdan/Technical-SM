package com.mdf.test.samir.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RepaymentSchedule(
    val installments: List<Installment>
) : Parcelable
