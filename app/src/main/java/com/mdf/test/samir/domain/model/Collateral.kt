package com.mdf.test.samir.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Collateral(
    val type: String,
    val value: Int
) : Parcelable