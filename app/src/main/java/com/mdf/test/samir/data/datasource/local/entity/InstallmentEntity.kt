package com.mdf.test.samir.data.datasource.local.entity

import androidx.room.ColumnInfo

data class InstallmentEntity(
    @ColumnInfo(name = "amount_due")
    val amountDue: Int,

    @ColumnInfo(name = "due_date")
    val dueDate: String
)