package com.mdf.test.samir.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Installment(
    val amountDue: Int,
    val dueDate: String
) : Parcelable