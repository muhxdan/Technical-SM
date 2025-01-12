package com.mdf.test.samir.data.datasource.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mdf.test.samir.data.datasource.local.dao.LoanDao
import com.mdf.test.samir.data.datasource.local.entity.LoanEntity
import com.mdf.test.samir.data.datasource.local.entity.converters.DocumentsConverter
import com.mdf.test.samir.data.datasource.local.entity.converters.InstallmentsConverter

@Database(entities = [LoanEntity::class], version = 1, exportSchema = false)
@TypeConverters(
    InstallmentsConverter::class, DocumentsConverter::class
)
abstract class AppDatabase : RoomDatabase() {
    /**
     * Returns the LoanDao for database operations related to loans.
     *
     * @return The LoanDao instance to interact with loan data.
     */
    abstract fun loanDao(): LoanDao
}