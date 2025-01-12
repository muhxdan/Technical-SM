package com.mdf.test.samir.data.datasource.remote.network

sealed class ApiResponse<out R> {

    /**
     * Represents a successful API response with data.
     *
     * @param data The data returned from the API.
     * @param T The type of the returned data.
     */
    data class Success<out T>(val data: T) : ApiResponse<T>()

    /**
     * Represents an error response from the API with an error message.
     *
     * @param errorMessage The message explaining what went wrong.
     */
    data class Error(val errorMessage: String) : ApiResponse<Nothing>()

    /**
     * Represents an empty response from the API, with no data.
     */
    data object Empty : ApiResponse<Nothing>()
}
