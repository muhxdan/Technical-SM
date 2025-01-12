package com.mdf.test.samir.data.datasource.local.entity

import androidx.room.ColumnInfo

data class DocumentEntity(
    @ColumnInfo(name = "doc_type")
    val type: String,

    @ColumnInfo(name = "doc_url")
    val url: String
)