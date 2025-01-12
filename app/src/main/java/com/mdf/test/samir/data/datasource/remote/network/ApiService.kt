package com.mdf.test.samir.data.datasource.remote.network

import com.mdf.test.samir.data.datasource.remote.response.LoanResponse
import retrofit2.http.GET

interface ApiService {

    /**
     * Fetches the list of loans from the API.
     *
     * This function performs a GET request to the "loans.json" endpoint and returns the loan data as a list.
     *
     * @return A list of LoanResponse objects representing the loan data.
     */
    @GET("loans.json")
    suspend fun getLoans(): List<LoanResponse>
}