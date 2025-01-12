package com.mdf.test.samir.data.datasource.local.entity

import androidx.room.ColumnInfo

data class CollateralEntity(
    @ColumnInfo(name = "type")
    val type: String,

    @ColumnInfo(name = "value")
    val value: Int
)