package com.mdf.test.samir.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Document(
    val type: String,
    val url: String
) : Parcelable
