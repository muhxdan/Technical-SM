package com.mdf.test.samir.data.datasource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.mdf.test.samir.data.datasource.local.entity.converters.DocumentsConverter


@Entity(tableName = "loans")
data class LoanEntity(
    @PrimaryKey
    val id: String,

    @ColumnInfo(name = "interest_rate")
    val interestRate: Double,

    @ColumnInfo(name = "amount")
    val amount: Int,

    @ColumnInfo(name = "purpose")
    val purpose: String,

    @TypeConverters(DocumentsConverter::class)
    @ColumnInfo(name = "documents")
    val documents: List<DocumentEntity>?,

    @Embedded(prefix = "borrower_")
    val borrower: BorrowerEntity,

    @ColumnInfo(name = "term")
    val term: Int,

    @Embedded(prefix = "collateral_")
    val collateral: CollateralEntity,

    @Embedded
    val repaymentSchedule: RepaymentScheduleEntity,

    @ColumnInfo(name = "risk_rating")
    val riskRating: String,

    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis()
)