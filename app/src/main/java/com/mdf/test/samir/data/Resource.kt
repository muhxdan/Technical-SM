package com.mdf.test.samir.data

sealed class Resource<T>(val data: T? = null, val message: String? = null) {

    /**
     * Represents a loading state where data is being fetched asynchronously.
     * It does not contain any data or message.
     */
    class Loading<T> : Resource<T>()

    /**
     * Represents a successful state with the fetched data.
     * Contains the data and no error message.
     *
     * @param data The successfully fetched data.
     */
    class Success<T>(data: T) : Resource<T>(data)

    /**
     * Represents an error state, indicating a failure occurred.
     * Contains an error message and optionally the data in case partial data was available.
     *
     * @param message The error message describing the failure.
     * @param data Optional data that might be available despite the error.
     */
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
}