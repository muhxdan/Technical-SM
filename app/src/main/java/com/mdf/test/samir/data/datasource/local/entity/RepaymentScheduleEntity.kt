package com.mdf.test.samir.data.datasource.local.entity

import androidx.room.ColumnInfo
import androidx.room.TypeConverters
import com.mdf.test.samir.data.datasource.local.entity.converters.InstallmentsConverter

data class RepaymentScheduleEntity(
    @TypeConverters(InstallmentsConverter::class)
    @ColumnInfo(name = "installments")
    val installments: List<InstallmentEntity>
)