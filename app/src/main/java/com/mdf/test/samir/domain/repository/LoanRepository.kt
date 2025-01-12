package com.mdf.test.samir.domain.repository

import com.mdf.test.samir.data.Resource
import com.mdf.test.samir.domain.model.Loan
import com.mdf.test.samir.domain.model.SortType
import kotlinx.coroutines.flow.Flow

interface LoanRepository {

    /**
     * Fetches the list of all loans.
     *
     * @return A Flow that emits a Resource containing the list of loans.
     * Resource handles loading, success, and error states.
     */
    fun getLoans(): Flow<Resource<List<Loan>>>

    /**
     * Fetches a sorted list of loans based on the specified sorting criteria.
     *
     * @param sortType Specifies the sorting order (e.g., by term, risk, etc.).
     * @return A Flow that emits a Resource containing the sorted list of loans.
     * Resource handles loading, success, and error states.
     */
    fun getLoansSorted(sortType: SortType): Flow<Resource<List<Loan>>>
}

