package com.mdf.test.samir.data.datasource.local

import com.mdf.test.samir.data.datasource.local.dao.LoanDao
import com.mdf.test.samir.data.datasource.local.entity.LoanEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val loanDao: LoanDao) {

    /**
     * Fetches all the loans from the local database.
     *
     * @return A Flow that emits a list of all loans in the database.
     */
    fun getLoans(): Flow<List<LoanEntity>> = loanDao.getLoans()

    /**
     * Inserts a list of loans into the local database.
     *
     * @param loans A list of LoanEntity objects to be inserted into the database.
     */
    suspend fun insertLoans(loans: List<LoanEntity>) = loanDao.insertLoans(loans)

    /**
     * Fetches a sorted list of loans from the local database based on the specified sorting criteria.
     *
     * @param sortType The sorting criterion (e.g., by term or risk rating).
     * The possible values for `sortType` are:
     * - 'TERM_ASC' for ascending order of `term`
     * - 'TERM_DESC' for descending order of `term`
     * - 'RISK_RATING_ASC' for ascending order of `risk_rating`
     * - 'RISK_RATING_DESC' for descending order of `risk_rating`
     * - Any other value falls back to sorting by `created_at`.
     *
     * @return A Flow that emits a list of loans sorted based on the provided sort type.
     */
    fun getLoansSorted(sortType: String): Flow<List<LoanEntity>> = loanDao.getLoansSorted(sortType)
}
