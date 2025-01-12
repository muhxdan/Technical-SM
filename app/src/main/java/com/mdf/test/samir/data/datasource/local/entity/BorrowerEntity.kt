package com.mdf.test.samir.data.datasource.local.entity

import androidx.room.ColumnInfo

data class BorrowerEntity(
    @ColumnInfo(name = "credit_score")
    val creditScore: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "borrower_id")
    val id: String,

    @ColumnInfo(name = "email")
    val email: String
)
