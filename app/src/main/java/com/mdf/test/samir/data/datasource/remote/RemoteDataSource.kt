package com.mdf.test.samir.data.datasource.remote

import com.mdf.test.samir.data.datasource.remote.network.ApiResponse
import com.mdf.test.samir.data.datasource.remote.network.ApiService
import com.mdf.test.samir.data.datasource.remote.response.LoanResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {

    /**
     * Fetches the list of loans from the remote API.
     * It performs the network request and returns the result wrapped in an ApiResponse.
     *
     * If the response is not empty, it emits a Success response with the loan data.
     * If the response is empty, it emits an Empty response.
     * If an error occurs during the request, it emits an Error response with the error message.
     *
     * @return A Flow emitting ApiResponse which can be Success, Empty, or Error.
     */
    fun getLoans(): Flow<ApiResponse<List<LoanResponse>>> = flow {
        try {
            val response = apiService.getLoans()

            if (response.isNotEmpty()) {
                emit(ApiResponse.Success(response))
            } else {
                emit(ApiResponse.Empty)
            }
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.toString()))
        }
    }.flowOn(Dispatchers.IO)
}