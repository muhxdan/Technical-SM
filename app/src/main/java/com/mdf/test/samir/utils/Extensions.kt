package com.mdf.test.samir.utils

import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Currency
import java.util.Locale

fun Int.toUsd(): String {
    return try {
        NumberFormat.getCurrencyInstance().apply {
            currency = Currency.getInstance("USD")
        }.format(this)
    } catch (e: Exception) {
        this.toString()
    }
}

fun String.toFormattedDate(): String {
    return try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd MMMM, yyyy", Locale.getDefault())
        val date = inputFormat.parse(this)
        date?.let { outputFormat.format(it) } ?: this
    } catch (e: Exception) {
        this
    }
}