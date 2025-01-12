package com.mdf.test.samir.data.datasource.local.entity.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mdf.test.samir.data.datasource.local.entity.InstallmentEntity

class InstallmentsConverter {
    @TypeConverter
    fun fromInstallmentsList(installments: List<InstallmentEntity>): String {
        return Gson().toJson(installments)
    }

    @TypeConverter
    fun toInstallmentsList(installmentsString: String): List<InstallmentEntity> {
        val type = object : TypeToken<List<InstallmentEntity>>() {}.type
        return Gson().fromJson(installmentsString, type)
    }
}
