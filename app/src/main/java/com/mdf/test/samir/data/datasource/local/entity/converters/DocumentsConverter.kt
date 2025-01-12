package com.mdf.test.samir.data.datasource.local.entity.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mdf.test.samir.data.datasource.local.entity.DocumentEntity

class DocumentsConverter {
    @TypeConverter
    fun fromDocumentsList(documents: List<DocumentEntity>?): String? {
        return if (documents == null) null else Gson().toJson(documents)
    }

    @TypeConverter
    fun toDocumentsList(documentsString: String?): List<DocumentEntity>? {
        if (documentsString == null) return null
        val type = object : TypeToken<List<DocumentEntity>>() {}.type
        return Gson().fromJson(documentsString, type)
    }
}
