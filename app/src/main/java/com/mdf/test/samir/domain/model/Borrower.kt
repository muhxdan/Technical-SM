package com.mdf.test.samir.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Borrower(
    val id: String,
    val name: String,
    val email: String,
    val creditScore: Int
) : Parcelable